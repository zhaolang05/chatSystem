package service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;


import comm.entity.User;
import data_access.FileUserDataAccessObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


import comm.entity.ChatMessage;
import comm.constant.MessageType;

@Component
@Slf4j
@ServerEndpoint(value="/chatserver/{userId}")
public class ChatServerService {

    private Session session;

    private static final CopyOnWriteArraySet<ChatServerService> webSockets = new CopyOnWriteArraySet<>();

    private static final Map<String, Session> sessionPool = new HashMap<>();
    private static final FileUserDataAccessObject fileUserDataAccessObject;

    static {
        try {
            fileUserDataAccessObject = new FileUserDataAccessObject("user.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {

            this.session = session;
            webSockets.add(this);
            sessionPool.put(userId, session);
            log.info("[ChatServer info]new user {} connect，current total connect: {}",userId, webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "userId") String userId) {
        try {
            webSockets.remove(this);
            sessionPool.remove(userId);
            log.info("[ChatServer info]user {} disconnect，current total connect: {}",userId,webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnMessage
    public void onMessage(String message,@PathParam(value = "userId") String userId) {
        log.info("[ChatServer info]receive user {} client info: {}",userId,message);
        Gson gson=new Gson();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("type", "check");//Business type
        jsonObject.addProperty("content", "heat break");//Message content
        try {
            ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
            switch (chatMessage.getMessageType()) {
                case MessageType.LOGIN:
                    String password=chatMessage.getData().get("password").toString();
                    if (fileUserDataAccessObject.existsByName(userId))
                    {

                        if (fileUserDataAccessObject.get(userId).getPassword().equals(password)) {
                            ChatMessage sendMessage=new ChatMessage();
                            sendMessage.setCode("0");
                            sendMessage.setMessageType(chatMessage.getMessageType());
                            sendMessage.setMessage("Login success!");
                            Map<String,Object> data=new HashMap<>();
                            data.put("username",userId);
                            sendMessage.setData(data);
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }
                        else
                        {
                            ChatMessage sendMessage=new ChatMessage();
                            sendMessage.setCode("-1");
                            sendMessage.setMessageType(chatMessage.getMessageType());
                            sendMessage.setMessage("password is not correct！");
                            Map<String,Object> data=new HashMap<>();
                            data.put("username",userId);
                            sendMessage.setData(data);
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }
                    }
                    else
                    {
                        ChatMessage sendMessage=new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.LOGIN);
                        sendMessage.setMessage("user is not exist！");
                        Map<String,Object> data=new HashMap<>();
                        data.put("username",userId);
                        sendMessage.setData(data);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }



                    break;
                case  MessageType.ADD_USER:
                    String userinfo=chatMessage.getData().get("user").toString();
                    User  user= gson.fromJson(userinfo, User.class);
                    if (fileUserDataAccessObject.existsByName(user.getName()))
                    {
                        ChatMessage sendMessage=new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(chatMessage.getMessageType());
                        sendMessage.setMessage("username:"+user.getName()+" is already exist！");
                        Map<String,Object> data=new HashMap<>();
                        data.put("user", user);
                        sendMessage.setData(data);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    else
                    {
                        fileUserDataAccessObject.save(user);
                        ChatMessage sendMessage=new ChatMessage();
                        sendMessage.setCode("0");
                        sendMessage.setMessageType(chatMessage.getMessageType());
                        sendMessage.setMessage("signup success！");
                        Map<String,Object> data=new HashMap<>();
                        data.put("user", user);
                        sendMessage.setData(data);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                        break;
            }
        }catch (Exception ex)
        {
            log.info("Json parse error:",ex);
            session.getAsyncRemote().sendText(jsonObject.toString());
        }

    }


    @OnError
    public void OnError(Session session, @PathParam(value = "userId") String userId, Throwable t) {
        try {
            if (session.isOpen()) {
                session.close();
            }
            webSockets.remove(this);
            sessionPool.remove(userId);
            log.info("[ChatServer info]连接[错误]断开，总数为: {}, 错误：{}", webSockets.size(), t.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        for (ChatServerService webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息(多人)
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
