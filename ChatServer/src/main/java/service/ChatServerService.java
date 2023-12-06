package service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;


import comm.entity.User;
import data_access.UserDataAccessObject;
import data_access.FriendDataAccessObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


import comm.entity.ChatMessage;
import comm.constant.MessageType;

@Component
@Slf4j
@ServerEndpoint(value = "/chatserver/{userId}")
public class ChatServerService {

    private Session session;

    private static final CopyOnWriteArraySet<ChatServerService> webSockets = new CopyOnWriteArraySet<>();

    private static final Map<String, Session> sessionPool = new HashMap<>();
    private static final UserDataAccessObject fileUserDataAccessObject;

    static {
        try {
            fileUserDataAccessObject = new UserDataAccessObject("user.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final FriendDataAccessObject friendDataAccessObject;

    static {
        try {
            friendDataAccessObject = new FriendDataAccessObject("friends.csv");
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
            log.info("[ChatServer info]new user {} connect，current total connect: {}", userId, webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "userId") String userId) {
        try {
            webSockets.remove(this);
            sessionPool.remove(userId);
            log.info("[ChatServer info]user {} disconnect，current total connect: {}", userId, webSockets.size());
        } catch (Exception e) {
        }
    }

    private Map<String,List<User>> getFriends(String userId)
    {
        Map<String, List<User>> friendList = new HashMap<>();
        if (friendDataAccessObject.get(userId)==null)
        {
            return friendList;
        }
        Map<String, List<String>> friendName = friendDataAccessObject.get(userId).getFriendsNameGroup();
        for (Map.Entry<String, List<String>> entry : friendName.entrySet()) {
            String group = entry.getKey();
            List<String> friends = entry.getValue();
            List<User> userlist = new ArrayList<>();
            for (int i = 0; i < friends.size(); i++) {
                if (fileUserDataAccessObject.existsByName(friends.get(i))) {
                    userlist.add(fileUserDataAccessObject.get(friends.get(i)));
                }
            }
            friendList.put(group, userlist);
        }
        return friendList;
    }
    @OnMessage
    public void onMessage(String message, @PathParam(value = "userId") String userId) {
        log.info("[ChatServer info]receive user {} client info: {}", userId, message);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "check");//Business type
        jsonObject.addProperty("content", "heat break");//Message content
        try {
            ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
            switch (chatMessage.getMessageType()) {
                case MessageType.LOGIN:
                    String password = chatMessage.getData().get("password").toString();
                    if (fileUserDataAccessObject.existsByName(userId)) {
                        if (fileUserDataAccessObject.get(userId).getPassword().equals(password)) {
                            ChatMessage sendMessage = new ChatMessage();
                            sendMessage.setCode("0");
                            sendMessage.setMessageType(chatMessage.getMessageType());
                            sendMessage.setMessage("Login success!");
                            Map<String, Object> data = new HashMap<>();
                            data.put("username", userId);
                            User onlineUser = fileUserDataAccessObject.get(userId);
                            onlineUser.setOnline(1);
                            data.put("user", onlineUser);
                            Map<String, List<User>> friendList=getFriends(userId);
//                            Map<String, List<User>> friendList = new HashMap<>();
//                            if (friendDataAccessObject.get(userId) != null) {
//                                Map<String, List<String>> friendName = friendDataAccessObject.get(userId).getFriendsNameGroup();
//                                for (Map.Entry<String, List<String>> entry : friendName.entrySet()) {
//                                    String group = entry.getKey();
//                                    List<String> friends = entry.getValue();
//                                    List<User> userlist = new ArrayList<>();
//                                    for (int i = 0; i < friends.size(); i++) {
//                                        if (fileUserDataAccessObject.existsByName(friends.get(i))) {
//                                            userlist.add(fileUserDataAccessObject.get(friends.get(i)));
//                                        }
//                                    }
//                                    friendList.put(group, userlist);
//                                }
//                                ;
//
//                            }
                            data.put("friends", friendList);
                            sendMessage.setData(data);
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                            //set user state on line
                            fileUserDataAccessObject.updateOnline(onlineUser);
                        } else {
                            ChatMessage sendMessage = new ChatMessage();
                            sendMessage.setCode("-1");
                            sendMessage.setMessageType(chatMessage.getMessageType());
                            sendMessage.setMessage("password is not correct！");
                            Map<String, Object> data = new HashMap<>();
                            data.put("username", userId);
                            sendMessage.setData(data);
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }
                    } else {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.LOGIN);
                        sendMessage.setMessage("user is not exist！");
                        Map<String, Object> data = new HashMap<>();
                        data.put("username", userId);
                        sendMessage.setData(data);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
                case MessageType.CHANGE_PASSWORD:
                    try {
                        Object data =  chatMessage.getData().get("user");
                        User user = gson.fromJson(gson.toJson(data), User.class);
                        fileUserDataAccessObject.save(user);
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        sendMessage.setMessageType(MessageType.CHANGE_PASSWORD);
                        sendMessage.setMessage("change success");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        log.info("user " + user.getName() + " change password");
                    } catch (Exception ex) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.CHANGE_PASSWORD);
                        sendMessage.setMessage("system error");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
                case MessageType.CHANGE_PROFILE:
                    try {
                        Object data =  chatMessage.getData().get("user");
                        User user = gson.fromJson(gson.toJson(data), User.class);
                        fileUserDataAccessObject.save(user);
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        sendMessage.setMessageType(MessageType.CHANGE_PROFILE);
                        sendMessage.setMessage("change success");
                        Map<String,Object> data1=new HashMap<>();
                        data1.put("user",user);
                        sendMessage.setData(data1);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        log.info("user " + user.getName() + " change profile");
                    } catch (Exception ex) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.CHANGE_PROFILE);
                        sendMessage.setMessage("system error");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
                case MessageType.CHANGE_USERNAME:
                    try {
                        String newUserName =  chatMessage.getData().get("newUserName").toString();
                        if (fileUserDataAccessObject.existsByName(newUserName))
                        {
                            ChatMessage sendMessage = new ChatMessage();
                            sendMessage.setCode("-1");
                            sendMessage.setMessageType(MessageType.CHANGE_USERNAME);
                            sendMessage.setMessage("user name "+newUserName+" already exist,Please choose another one");
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                            return;
                        }
                        User user=fileUserDataAccessObject.get(userId);
                        friendDataAccessObject.changeUserName(userId,newUserName);
                        user.setName(newUserName);
                        fileUserDataAccessObject.save(user);
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        sendMessage.setMessageType(MessageType.CHANGE_USERNAME);
                        sendMessage.setMessage("change success");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        log.info("user " + user.getName() + " change user Name");
                    } catch (Exception ex) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.CHANGE_USERNAME);
                        sendMessage.setMessage("system error");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
                case MessageType.SEND_MESSAGE:
                    String touser = chatMessage.getData().get("touser").toString();
                    if (sessionPool.get(touser) != null) {

                        String receivemessage = chatMessage.getData().get("message").toString();
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        sendMessage.setMessageType(MessageType.SEND_MESSAGE);
                        Map<String, Object> data1 = new HashMap<>();
                        data1.put("fromuser", userId);
                        data1.put("message", receivemessage);
                        sendMessage.setData(data1);
                        sessionPool.get(touser).getAsyncRemote().sendText(gson.toJson(sendMessage));
                    } else {
                        //save offline message;
                        log.info("offline message");
                    }
                    break;
                case MessageType.ADD_FRIEND:
                    try {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        String touser1=chatMessage.getData().get("username").toString();
                        friendDataAccessObject.addFriend(touser1,fileUserDataAccessObject.get(userId));
                        friendDataAccessObject.addFriend(userId,fileUserDataAccessObject.get(touser1));
                        sendMessage.setMessageType(MessageType.REFRESH_FRIENDS);
                        Map<String, Object> data2 = new HashMap<>();
                        data2.put("fromuser", userId);
                        data2.put("touser", touser1);
//                        data2.put("friends",friendDataAccessObject.get(touser1).getFriendsGroup());
                        Map<String, List<User>> friendList=getFriends(touser1);
                        data2.put("friends",friendList);
                        sendMessage.setData(data2);
                        if (sessionPool.get(touser1)!=null) {
                            sessionPool.get(touser1).getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }

                        sendMessage.setMessageType(MessageType.REFRESH_FRIENDS);
//                        data2.put("friends",friendDataAccessObject.get(userId));
                        friendList=getFriends(userId);
                        data2.put("friends",friendList);
                        sendMessage.setData(data2);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }catch (Exception ex) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.ADD_FRIEND);
                        sendMessage.setMessage("system error");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }

                    break;
                case MessageType.ADD_FRIEND_APPROVE:
                    try {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("1");
                        String touser1=chatMessage.getData().get("touser").toString();
                        sendMessage.setMessageType(chatMessage.getMessageType());
                        sendMessage.setMessage("request add  as a friend");
                        sendMessage.setCode(chatMessage.getCode());
                        if (chatMessage.getCode().equals("0"))
                        {
                            friendDataAccessObject.addFriend(touser1,fileUserDataAccessObject.get(userId));
                            friendDataAccessObject.addFriend(userId,fileUserDataAccessObject.get(touser1));
                            sendMessage.setMessageType(MessageType.ADD_FRIEND_APPROVE);
                            Map<String, Object> data2 = new HashMap<>();
                            data2.put("fromuser", userId);
                            data2.put("touser", touser1);
                            data2.put("friends",friendDataAccessObject.get(touser1));
                            sendMessage.setData(data2);
                            sessionPool.get(touser1).getAsyncRemote().sendText(gson.toJson(sendMessage));
                            //refresh friends
                            sendMessage.setMessageType(MessageType.REFRESH_FRIENDS);
                            data2.put("friends",friendDataAccessObject.get(userId));
                            sendMessage.setData(data2);
                            session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }
                        else
                        {
                            sendMessage.setMessageType(MessageType.ADD_FRIEND);
                            sendMessage.setMessage("3");
                            sessionPool.get(touser1).getAsyncRemote().sendText(gson.toJson(sendMessage));
                        }

                    }catch (Exception ex) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(MessageType.ADD_FRIEND);
                        sendMessage.setMessage("system error");
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
                case MessageType.GET_USERS:
                    ChatMessage getUserMessage= new ChatMessage();
                    Map<String,Object> dataUsers=new HashMap<>();
                    getUserMessage.setMessageType(MessageType.GET_USERS);
                    dataUsers.put("users",fileUserDataAccessObject.getUsers());
                    getUserMessage.setData(dataUsers);
                    session.getAsyncRemote().sendText(gson.toJson(getUserMessage));
                    break;
                case MessageType.ADD_USER:
                    String userinfo = gson.toJson(chatMessage.getData().get("user"));
                    User user = gson.fromJson(userinfo, User.class);
                    if (fileUserDataAccessObject.existsByName(user.getName())) {
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("-1");
                        sendMessage.setMessageType(chatMessage.getMessageType());
                        sendMessage.setMessage("username:" + user.getName() + " is already exist！");
                        Map<String, Object> data2 = new HashMap<>();
                        data2.put("user", user);
                        sendMessage.setData(data2);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    } else {
                        int id = 1000;

                        fileUserDataAccessObject.save(user);
                        ChatMessage sendMessage = new ChatMessage();
                        sendMessage.setCode("0");
                        sendMessage.setMessageType(chatMessage.getMessageType());
                        sendMessage.setMessage("signup success！");
                        Map<String, Object> data3 = new HashMap<>();
                        data3.put("user", user);
                        sendMessage.setData(data3);
                        session.getAsyncRemote().sendText(gson.toJson(sendMessage));
                    }
                    break;
            }
        } catch (Exception ex) {
            log.info("Json parse error:", ex);
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
            log.info("[ChatServer info]Connection [Error] Dropped, Total is: {}, Error: {}", webSockets.size(), t.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast Message
    public void sendAllMessage(String message) {
        log.info("[WebSocket Message]Broadcast Message:" + message);
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

    // Single Message
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("[WebSocket Message] Single Point Message:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Single Message (multi)
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("[WebSocket Message] Single Point Message:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
