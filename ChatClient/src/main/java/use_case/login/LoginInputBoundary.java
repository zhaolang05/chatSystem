package use_case.login;

import org.java_websocket.client.WebSocketClient;

import java.net.URISyntaxException;

public interface LoginInputBoundary {
    WebSocketClient execute(LoginInputData loginInputData) throws URISyntaxException, InterruptedException;
    void toSignup();
}
