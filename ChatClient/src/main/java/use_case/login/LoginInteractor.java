package use_case.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.ChatClientWebSocket;
import org.java_websocket.client.WebSocketClient;

import java.net.URISyntaxException;
import java.util.*;

public class LoginInteractor implements LoginInputBoundary, EventListener {

    final LoginOutputBoundary loginPresenter;
    private WebSocketClient webSocketClient;


    public LoginInteractor(LoginOutputBoundary loginOutputBoundary) {
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void toSignup() {
        loginPresenter.toSignup();
    }

    @Override
    public WebSocketClient execute(LoginInputData loginInputData) throws URISyntaxException, InterruptedException {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        ChatClientWebSocket chatClientWebSocket = new ChatClientWebSocket();
        WebSocketClient webSocketClient = chatClientWebSocket.createWebSocket(username);
        chatClientWebSocket.eventSource.addEventListener(this);

        Gson gson = new Gson();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(MessageType.LOGIN);
        Map<String, Object> data = new HashMap<>();
        data.put("password", password);
        chatMessage.setData(data);

        webSocketClient.send(gson.toJson(chatMessage));
        this.webSocketClient = webSocketClient;
        return webSocketClient;
    }




    @Override
    public void handleEvent(Event event) {
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(event.getData().toString(), ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.LOGIN)) {
            Map<String, Object> data = chatMessage.getData();
            String username = data.get("username").toString();
            if (chatMessage.getCode().equals("-1")) {
                loginPresenter.prepareFailView(username + ": " + chatMessage.getMessage());
            } else {

                User user = gson.fromJson(gson.toJson(data.get("user")), User.class);
                Object f = data.get("friends");
                String gsondata = gson.toJson(f);
                Map<String, List<User>> friends = gson.fromJson(gsondata, new TypeToken<Map<String, List<User>>>() {
                }.getType());
                LoginOutputData loginOutputData = new LoginOutputData(user, friends, false);
                if (webSocketClient.isOpen()) {
                    webSocketClient.close();
                }
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}