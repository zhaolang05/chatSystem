package use_case.chat;

import com.google.gson.Gson;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.ChatClientWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import view.AddFriendFrame;
import view.ChangePasswordFrame;
import view.ChangeUserNameFrame;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class ChatInteractor implements ChatInputBoundary, EventListener {

    final ChatOutputBoundary chatMainPresenter;
    private WebSocketClient webSocketClient;
    private Map<String, Chat> chatWindows = new HashMap<>();
    private String userName;

    private ChatClientWebSocket chatClientWebSocket;


    public ChatInteractor(ChatOutputBoundary chatOutputBoundary) {
        this.chatMainPresenter = chatOutputBoundary;
    }

    @Override
    public void toChat() {
        chatMainPresenter.toChat();
    }

    @Override
    public void execute(ChatInputData chatInputData) {
        String username = chatInputData.getUser().getName();
        try {
            this.userName = userName;
            connect(username);
        } catch (Exception ex) {
            log.error("start websocket error", ex);
        }
    }

    private void connect(String username) throws URISyntaxException, InterruptedException {
        ChatClientWebSocket chatClientWebSocket = new ChatClientWebSocket();
        WebSocketClient webSocketClient = chatClientWebSocket.createWebSocket(username);
        chatClientWebSocket.eventSource.addEventListener(this);
        this.chatClientWebSocket = chatClientWebSocket;
        this.webSocketClient = webSocketClient;
    }

    @Override
    public void registerChatWindows(String userName, Chat chat) {
        chatWindows.put(userName, chat);
        this.chatClientWebSocket.eventSource.addEventListener(chat);
    }

    @Override
    public void registerChangePasswordWindow(ChangePasswordFrame changePasswordFrame) {
        this.chatClientWebSocket.eventSource.addEventListener(changePasswordFrame);
    }

    @Override
    public void registerListener(EventListener listener) {
        this.chatClientWebSocket.eventSource.addEventListener(listener);
    }


    @Override
    public void registerChangeUserNameWindow(ChangeUserNameFrame changeUserNameFrame) {
        this.chatClientWebSocket.eventSource.addEventListener(changeUserNameFrame);
    }

    @Override
    public void registerAddFriendWindow(AddFriendFrame addFriendFrame) {
        this.chatClientWebSocket.eventSource.addEventListener(addFriendFrame);
    }
    @Override
    public void disposeChatWindow()
    {
        for (EventListener listener :  this.chatClientWebSocket.eventSource.getListeners())
        {
            if (listener instanceof  Chat)
            {
                ((Chat) listener).dispose();
            }
        }
    }


    @Override
    public void sendMessage(ChatMessage message) {
        Gson gson = new Gson();
        if (this.webSocketClient.isClosed()) {
            try {
                connect(userName);
            } catch (Exception ex) {
                log.error("connect error", ex);
            }
        }
        this.webSocketClient.send(gson.toJson(message));
    }

    @Override
    public void sendMessage(String friend, String message, String messageType) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(messageType);
        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        data.put("touser", friend);
        chatMessage.setData(data);
        Gson gson = new Gson();
        if (this.webSocketClient.isClosed()) {
            try {
                connect(userName);
            } catch (Exception ex) {
                log.error("connect error", ex);
            }
        }
        this.webSocketClient.send(gson.toJson(chatMessage));
    }

    @Override
    public void sendMessage(User user, String messageType) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(messageType);
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        chatMessage.setData(data);
        Gson gson = new Gson();
        if (this.webSocketClient.isClosed()) {
            try {
                connect(userName);
            } catch (Exception ex) {
                log.error("connect error", ex);
            }
        }
        this.webSocketClient.send(gson.toJson(chatMessage));
    }


    @Override
    public void handleEvent(Event event) {
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(event.getData().toString(), ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.SEND_MESSAGE)) {
            Map<String, Object> data = chatMessage.getData();
            String username = data.get("fromuser").toString();
            String message = data.get("message").toString();
            Chat chat = chatWindows.get(username);
            if (chat != null) {
                chat.setReceivePaneText(false, message);
            }
        }
    }
}