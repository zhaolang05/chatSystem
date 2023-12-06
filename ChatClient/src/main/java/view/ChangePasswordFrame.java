/*
 * ChatFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import app.ChatClientApplication;
import com.google.gson.Gson;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.chat.ChatController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import view.comm.MyTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordFrame extends javax.swing.JFrame implements EventListener {

    /**
     * Creates new form ChangePasswordFrame
     */
    private User user;
    private ChatController controller;
    private JButton confirmButton;
    private JPasswordField passwordInputField;

    private JPasswordField repeatPasswordInputField;
    private JPasswordField oldPasswordInputField;
    private ChatMainView parent;

    public ChangePasswordFrame(ChatController controller, User user,ChatMainView chatMainView) {
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
        this.setTitle("Change Password");
        this.setLayout(new  GridLayout(6,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        setBounds(100, 100, 130, 600);
        this.setSize(50,600);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(new Color(255, 255, 255));

        JTextField usernameInputField = new JTextField(21);
        usernameInputField.setText(user.getName());
        usernameInputField.setEnabled(false);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("User Name"), usernameInputField);
        userInfoPanel.add(usernameInfo);

        oldPasswordInputField = new JPasswordField(20);
        userInfoPanel.add(oldPasswordInputField);
        LabelTextPanel oldPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.OLD_PASSWORD_LABEL), oldPasswordInputField);
        userInfoPanel.add(oldPasswordInfo);


        passwordInputField = new JPasswordField(18);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        userInfoPanel.add(passwordInfo);

        repeatPasswordInputField = new JPasswordField(16);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        userInfoPanel.add(repeatPasswordInfo);


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
                            changePassword();
                        }
                    }
                }
        );

        this.add(usernameInfo);
        this.add(oldPasswordInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
        MyTools.setWindowsMiddleShow(this);
        pack();
    }


    @Override
    public void handleEvent(Event event) {
        String message = event.getData().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        if (chatMessage.getMessageType().equals(MessageType.CHANGE_PASSWORD)) {
            if (chatMessage.getCode().equals("1")) {
                JOptionPane.showMessageDialog(this, "password changed,restart");
                controller.disposeChatWindow();
                this.parent.application.setVisible(true);
                this.parent.dispose();
                this.dispose();
            }
        }
    }

    private void changePassword() {
        String oldPassword = new String(oldPasswordInputField.getPassword());
        String password = new String(passwordInputField.getPassword());
        String repeatPassword = new String(repeatPasswordInputField.getPassword());
        if (!oldPassword.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(this, "old password is wrong");
            return;
        }
        if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(this, "repeat password is not equals password");
            return;
        }
        user.setPassword(new String(passwordInputField.getPassword()));
        Gson gson = new Gson();
        controller.sendMessage(user, MessageType.CHANGE_PASSWORD);
    }
}