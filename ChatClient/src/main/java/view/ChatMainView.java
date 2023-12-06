package view;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatViewModel;
import lombok.extern.slf4j.Slf4j;
import use_case.chat.Chat;
import use_case.chat.ChatInputData;
import use_case.chat.GroupChat;
import use_case.chat.MyTree;
import view.comm.MyLabel;
import view.comm.MyTools;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.JOptionPane.YES_NO_OPTION;

@SuppressWarnings("ALL")
@Slf4j
public class ChatMainView extends MainFrame implements ActionListener, EventListener {
    public final String viewName = "Chat Main";
    final int width = 350;//form width
    final int height = 630;//Form height
    private String userName;
    private ChatController controller;
    private ChatViewModel chatViewModel;
    private Map<String, List<User>> friends;
    public GroupChat groupChat;//Group chat rooms
    private User user = new User();
    public JFrame application;

    public ChatMainView(ChatViewModel chatViewModel, ChatController controller) {
        this.controller = controller;
        this.chatViewModel = chatViewModel;

    }


    /**
     * Initialize the main form
     */
    public void init(ChatInputData chatInputData) {
        new MyLabel(settingLabel, "/img/button/QQ_settings", "png").addEvent();//Add an event to the settings button at the bottom
        MyTools.setWindowsMiddleShow(this, width, height);//Set the form to be displayed in the center
        initUserStatus();//Initialize whether the user is online or not
        this.application = chatInputData.getApplication();
        User user1 = chatInputData.getUser();
        initUser(user1);
        this.tagLabel.setText(chatInputData.getUser().getPersonalizedSign());
        this.friends = chatInputData.getFriendList();
        initTree(friends);
        addEvent();
        controller.execute(chatInputData);
        controller.registerListener(this);
    }

    public void initUser(User user) {
        this.user = user;
        this.userName = user.getName();
        this.userNameLabel.setText(this.userName);
        if (user.getAvatar() != null) {
            if (!user.getAvatar().isEmpty()) {
                this.avatarLabel.setIcon(MyTools.getIcon("/img/headImage/middle/" + user.getAvatar() + "_64.jpg"));
            }
        }
    }

    public void addEvent() {
        this.tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 2) {
                        log.info("double click：" + selRow + "," + selPath);
                        if (selPath.getPathCount() == 3) {
                            openChat();
                        }

                    }

                }

            }

        });
    }


    /**
     * Initialize whether the user is online or not
     */
    public void initUserStatus() {
        statusComboBox.removeAllItems();
        statusComboBox.addItem(MyTools.getIcon("/img/status/status_online.png"));
        statusComboBox.addItem(MyTools.getIcon("/img/status/status_qme.png"));
        statusComboBox.addItem(MyTools.getIcon("/img/status/status_leave.png"));
        statusComboBox.addItem(MyTools.getIcon("/img/status/status_busy.png"));
        statusComboBox.addItem(MyTools.getIcon("/img/status/status_invisible.png"));
    }

    /**
     * Initialize the friends list
     */
    public void initTree(Map<String, List<User>> friendNames) {
        new MyTree(tree, friendNames);
    }

    /*
     * Start chatting with your friend
     */
    @Override
    public void startChat(ActionEvent e) {
        if (tree.getSelectionPath().getPathCount() == 3) {
            openChat();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, you didn't select any friend！");
        }
    }

    @Override
    public void addFriend() {
        List<User> users = new ArrayList<>();
        users.add(user);
        AddFriendFrame addFriendFrame = new AddFriendFrame(controller, users, user);
        addFriendFrame.setVisible(true);
    }

    @Override
    public void changePassword(ActionEvent e) {
        ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame(controller, user, this);
        changePasswordFrame.setVisible(true);
    }

    @Override
    public void changeProfile(ActionEvent e) {
        ChangeProfileFrame changeProfileFrame = new ChangeProfileFrame(controller, user, this);
        changeProfileFrame.setVisible(true);
    }


    @Override
    public void changeUserName(ActionEvent e) {
        ChangeUserNameFrame changeUserNameFrame = new ChangeUserNameFrame(controller, user, this);
        changeUserNameFrame.setVisible(true);
    }

    private void openChat() {
        String friendName = tree.getSelectionPath().getLastPathComponent().toString();
        Chat chat = new Chat(controller, friendName, this.userNameLabel.getText());
        chat.setVisible(true);
        controller.registerChatWindows(friendName, chat);
    }

    /*
     * Request a friend's profile
     */
    @Override
    public void getFriendInfo(ActionEvent e) {
        String str = tree.getSelectionPath().getLastPathComponent().toString();
        String userName = str.substring(0, str.indexOf("("));

    }

    /**
     * Send a file
     *
     * @param e
     */
    @Override
    public void sendFile(ActionEvent e) {
    }

    /**
     * Delete a friend
     *
     * @param e
     */
    public void deleteFriend(ActionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        node.removeFromParent();
        JOptionPane.showMessageDialog(null, "Delete friend successfully! Please close and open the current group to refresh your friends list!");
    }

    /**
     * Enter the chat room
     */
    @Override
    public void gotoChatRoom() {
//		GroupChat=new GroupChat(this);
    }

    /**
     * Create a new chat room
     */
    @Override
    public void buildNewChatRoom() {
        JOptionPane.showMessageDialog(null, "The new chat room is an exclusive function for members, you are not a member yet, whether you want to join a member, more functions are waiting for you to play, only $1  per month!", "Infomation", JOptionPane.YES_NO_CANCEL_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void handleEvent(Event event) {
        String message = event.getData().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        switch (chatMessage.getMessageType()) {
            case MessageType.REFRESH_FRIENDS:
                if (chatMessage.getCode().equals("1")) {
                    Map<String, Object> data = chatMessage.getData();
                    friends = gson.fromJson(gson.toJson(data.get("friends")), new TypeToken<Map<String, List<User>>>() {}.getType());
                    initTree(friends);
                } else {
                    JOptionPane.showMessageDialog(this, chatMessage.getMessage());
                }
                break;
            case MessageType.ADD_FRIEND:
                if (chatMessage.getCode().equals("1")) {
                    Map<String, Object> data = chatMessage.getData();
                    String fromuser = data.get("fromuser").toString();
                    String msg = "User :" + fromuser + " ask to be added as a friend, do you agree?";
                    int result = JOptionPane.showConfirmDialog(this, msg, "Approve add friend request", YES_NO_OPTION);
                    ChatMessage chatMessage1 = new ChatMessage();
                    chatMessage1.setMessageType(MessageType.ADD_FRIEND_APPROVE);
                    chatMessage1.setCode(String.valueOf(result));
                    Map<String, Object> data1 = new HashMap<>();
                    data1.put("fromuser", user.getName());
                    data1.put("touser", fromuser);
                    this.controller.sendMessage(chatMessage1);
                }
                break;
            case MessageType.ADD_FRIEND_APPROVE:
                if (chatMessage.getCode().equals("1")) {
                    Map<String, Object> data = chatMessage.getData();
                    String fromuser = data.get("fromuser").toString();
                    if (!fromuser.equals(user.getName())) {
                        String msg = "User :" + fromuser + " Agree to be added as a friend";
                        JOptionPane.showMessageDialog(this, msg);
                        friends = (Map<String, List<User>>) data.get("friends");
                        initTree(friends);
                    }
                }
                break;
        }

    }
}
