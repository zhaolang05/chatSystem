package use_case.login;

import com.google.gson.Gson;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import interface_adapter.ChatClientWebSocket;
import org.java_websocket.client.WebSocketClient;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class LoginInteractor implements LoginInputBoundary, Observer {

    final LoginOutputBoundary loginPresenter;


    public LoginInteractor(LoginOutputBoundary loginOutputBoundary) {
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void toSignup()
    {
        loginPresenter.toSignup();
    }
    @Override
    public WebSocketClient execute(LoginInputData loginInputData) throws URISyntaxException, InterruptedException {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        ChatClientWebSocket chatClientWebSocket= new ChatClientWebSocket();
        WebSocketClient webSocketClient =chatClientWebSocket.createWebSocket(username);
        chatClientWebSocket.addObserver(this);

        Gson gson = new Gson();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(MessageType.LOGIN);
        Map<String, Object> data = new HashMap<>();
        data.put("password", password);
        chatMessage.setData(data);

        webSocketClient.send(gson.toJson(chatMessage));
        return webSocketClient;
    }

    @Override
    public void update(Observable o, Object arg) {
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(arg.toString(), ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.LOGIN)) {
            Map<String, Object> data = chatMessage.getData();
            String username = data.get("username").toString();
            if (chatMessage.getCode().equals("-1")) {
                loginPresenter.prepareFailView(username + ": " + chatMessage.getMessage());
            } else {
                LoginOutputData loginOutputData = new LoginOutputData(username, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }


}