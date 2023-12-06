/*
 * ChatFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import com.google.gson.Gson;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.chat.ChatController;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import view.comm.MyTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeProfileFrame extends JFrame implements EventListener {

    /**
     * Creates new form ChangePasswordFrame
     */
    private User user;
    private ChatController controller;
    private JButton confirmButton;
    private ChatMainView parent;
    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField personalizedSignInputField = new JTextField(15);
    private final JTextField avatarInputField = new JTextField(11);
    private final JTextArea profileInputField = new JTextArea(2, 18);
    public JComboBox<ImageIcon> avatarComboBox;


    public ChangeProfileFrame(ChatController controller, User user, ChatMainView chatMainView) {
        this.controller = controller;
        this.controller.registerListener(this);
        this.user = user;
        this.parent = chatMainView;
        initComponents(user);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(User user) {
        this.setTitle("Change profile");
        this.setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("User Name"), usernameInputField);

        usernameInputField.setText(user.getName());
        usernameInputField.setEnabled(false);
        personalizedSignInputField.setText(user.getPersonalizedSign());
        LabelTextPanel personalizedSignInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PERSONALIZEDSIGN_LABEL), personalizedSignInputField);
        JPanel profileInfo = new JPanel();
        profileInfo.add(new JLabel(SignupViewModel.PROFILE_LABEL));
        profileInfo.add(profileInputField);
        profileInputField.setText(user.getProfile());


        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(new Color(255, 255, 255));
        JTextField usernameInputField = new JTextField(21);
        usernameInputField.setText(user.getName());
        usernameInputField.setEnabled(false);

        avatarComboBox = new JComboBox<ImageIcon>();
        JPanel avatarInfo = new JPanel();
        avatarInfo.add(new JLabel(SignupViewModel.AVATAR_LABEL));
        avatarInfo.add(avatarComboBox);
        avatarInputField.setEnabled(false);
        avatarInputField.setBackground(Color.getColor("EEEEEE"));
        avatarInfo.add(avatarInputField);
        avatarComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                String command = event.getActionCommand();
                if ("comboBoxChanged".equals(command)) {
                    avatarInputField.setText(String.valueOf(comboBox.getSelectedIndex()));
                }
            }
        });
        initavatar();
        if (user.getAvatar() != null) {
            if (!user.getAvatar().isEmpty()) {
                avatarComboBox.setSelectedIndex(Integer.parseInt(user.getAvatar()));
            }
        }

        JPanel buttons = new JPanel();
        confirmButton = new JButton("Confirm");
        buttons.add(confirmButton);
        JButton cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);

        confirmButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirmButton)) {
                            changeProfile();
                        }
                    }
                }
        );

        this.add(usernameInfo);
        this.add(profileInfo);
        this.add(personalizedSignInfo);
        this.add(avatarInfo);
        this.add(buttons);
        MyTools.setWindowsMiddleShow(this, 50, 400);
        pack();
    }

    public void initavatar() {
        avatarComboBox.removeAllItems();
        for (int i = 0; i < 16; i++) {
            avatarComboBox.addItem(MyTools.getIcon("/img/headImage/small/" + i + "_32.jpg"));
        }
    }

    @Override
    public void handleEvent(Event event) {
        String message = event.getData().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.CHANGE_PROFILE)) {
            if (chatMessage.getCode().equals("1")) {
                JOptionPane.showMessageDialog(this, "profile changed success");
                String json = gson.toJson(chatMessage.getData().get("user"));
                User user = gson.fromJson(json, User.class);
                this.parent.initUser(user);
                this.dispose();

            }
        }
    }

    private void changeProfile() {

        User newUser = user.getCopy();
        newUser.setProfile(profileInputField.getText());
        newUser.setAvatar(avatarInputField.getText());
        newUser.setPersonalizedSign(personalizedSignInputField.getText());
        controller.sendMessage(newUser, MessageType.CHANGE_PROFILE);
    }
}