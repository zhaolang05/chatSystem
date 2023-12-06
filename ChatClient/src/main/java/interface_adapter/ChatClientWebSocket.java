package interface_adapter;

import comm.event.Event;
import comm.event.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;


@Slf4j
public class ChatClientWebSocket {
    public EventSource eventSource = new EventSource();
    private WebSocketClient webSocketClient;

    public WebSocketClient createWebSocket(String userId) throws URISyntaxException, InterruptedException {
        String url = String.format("ws://127.0.0.1:8080/chatserver/%s", userId);
        URI uri = new URI(url);
        WebSocketClient webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }

            @Override
            public void onMessage(String s) {
                ChatClientWebSocket.this.notify(s);
                log.info("user:{},receive message:{}", userId, s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log.info("user:{},close connect:{}", userId);
            }

            @Override
            public void onError(Exception e) {

            }
        };
        int timeout = 5000; // 设置连接超时时间为5秒
        webSocketClient.setConnectionLostTimeout(timeout);

        webSocketClient.connectBlocking();
        while (!(ReadyState.OPEN).equals(webSocketClient.getReadyState())) {
            log.info("socket connect is not yet open");
        }

        this.webSocketClient = webSocketClient;
        return webSocketClient;
    }

    private void notify(String message) {
        Event event=new Event("System","message",message);
        eventSource.fireEvent(event);
    }

}
