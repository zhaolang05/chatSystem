package use_case.signup;

import com.google.gson.Gson;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.ChatClientWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;


import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
public class SignupInteractor implements SignupInputBoundary, EventListener {

    final SignupOutputBoundary userPresenter;
    private ChatClientWebSocket chatClientWebSocket;
    private WebSocketClient webSocketClient;

    public SignupInteractor(SignupOutputBoundary signupOutputBoundary) {
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            return;
        }
        Date now = new Date();
        String username = signupInputData.getUsername();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        User user = new User(signupInputData.getUsername(), signupInputData.getPassword(),signupInputData.getProfile(), sdf.format(now),signupInputData.getPersonalizedSign(),signupInputData.getAvatar());
        chatClientWebSocket = new ChatClientWebSocket();
        try {
            webSocketClient = chatClientWebSocket.createWebSocket(username);
            chatClientWebSocket.eventSource.addEventListener(this);
            Gson gson = new Gson();
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessageType(MessageType.ADD_USER);
            Map<String, Object> data = new HashMap<>();
            data.put("user", user);
            chatMessage.setData(data);
            webSocketClient.send(gson.toJson(chatMessage));
        } catch (Exception ex) {
            log.error("websocket connect error", ex);
        }
    }

    @Override
    public void toLogin() {
        userPresenter.toLogin();
    }



    @Override
    public void handleEvent(Event event) {
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(event.getData().toString(), ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.ADD_USER)) {
            Map<String, Object> data = chatMessage.getData();

            String userinfo=gson.toJson(chatMessage.getData().get("user"));
            User  user= gson.fromJson(userinfo, User.class);
            if (chatMessage.getCode().equals("-1")) {
                userPresenter.prepareFailView(user.getName() + ": " + chatMessage.getMessage());
            } else {
                SignupOutputData loginOutputData = new SignupOutputData(user.getName(), user.getCreationTime(), false);
                userPresenter.prepareSuccessView(loginOutputData);
            }
            if (webSocketClient.isOpen()) {
                webSocketClient.close();
            }
        }
    }
}