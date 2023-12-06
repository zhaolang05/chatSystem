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

import view.comm.MyTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChangeUserNameFrame extends JFrame implements EventListener {

    /**
     * Creates new form ChangeUserNameFrame
     */
    private User user;
    private ChatController controller;
    private JButton confirmButton;
    private JTextField newUserNameInputField;
    private JPasswordField passwordInputField;

    private ChatMainView parent;


    public ChangeUserNameFrame(ChatController controller, User user,ChatMainView chatMainView) {


        this.controller = controller;
        this.controller.registerListener(this);
        this.user = user;
        this.parent=chatMainView;
        initComponents(user);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(User user) {
        this.setTitle("Change User Name");
        this.setLayout(new  GridLayout(6,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.setSize(50,600);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(new Color(255, 255, 255));

        JTextField usernameInputField = new JTextField(21);
        usernameInputField.setText(user.getName());
        usernameInputField.setEnabled(false);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("User Name"), usernameInputField);

        passwordInputField = new JPasswordField(21);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);


        newUserNameInputField = new JTextField(15);
        usernameInputField.setText(user.getName());
        usernameInputField.setEnabled(false);

        LabelTextPanel newUsernameInfo = new LabelTextPanel(
                new JLabel("Choice new user Name"), newUserNameInputField);

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
                            changeUserName();
                        }
                    }
                }
        );

        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(newUsernameInfo);



        this.add(buttons);
        MyTools.setWindowsMiddleShow(this);
        pack();
    }


    @Override
    public void handleEvent(Event event) {
        String message = event.getData().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.CHANGE_USERNAME)) {
            if (chatMessage.getCode().equals("1")) {
                JOptionPane.showMessageDialog(this, "user name changed,restart");
                controller.disposeChatWindow();
                this.parent.application.setVisible(true);
                this.parent.dispose();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, chatMessage.getMessage());
            }
        }
    }

    private void changeUserName() {

        String password = new String(passwordInputField.getPassword());
        String userName=newUserNameInputField.getText();

        if (!password.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(this, "password is wrong");
            return;
        }
        if (userName.equals(user.getName())) {
            JOptionPane.showMessageDialog(this, "New user name are the same as old user name!");
            return;
        }
        ChatMessage message=new ChatMessage();
        message.setMessageType(MessageType.CHANGE_USERNAME);
        Map<String,Object> data=new HashMap<>();
        data.put("newUserName",userName);
       message.setData(data);
        controller.sendMessage(message);
    }
}