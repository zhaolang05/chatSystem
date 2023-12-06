package use_case.chat;

import comm.entity.User;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class ChatInputData {

    private User user;
    private Map<String, List<User>> friendList;

    public JFrame getApplication() {
        return application;
    }

    public void setApplication(JFrame application) {
        this.application = application;
    }

    private JFrame application;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, List<User>> getFriendList() {
        return friendList;
    }

    public void setFriendList(Map<String, List<User>> friendList) {
        this.friendList = friendList;
    }
}
