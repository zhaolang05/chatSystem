/*
 * ChatFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author __USER__
 */
public class ChatFrame extends javax.swing.JFrame implements ActionListener, PropertyChangeListener {

    /**
     * Creates new form ChatFrame
     */
    public ChatFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        rightPanel = new javax.swing.JPanel();
        showOtherLabel = new javax.swing.JLabel();
        showSelfLabel = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        avatarLabel = new javax.swing.JLabel();
        myFriendLabel = new javax.swing.JLabel();

        SendFileLabel = new javax.swing.JLabel();
//        addFriendLabel = new javax.swing.JLabel();
        chatPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiveTextPane = new javax.swing.JTextPane();
        sendPanel = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        javax.swing.JPanel toolPanel = new javax.swing.JPanel();
        fontLabel = new javax.swing.JLabel();
        emoticonsLabel = new javax.swing.JLabel();
        emoticonsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectFace();
            }
        });
        sendPictureLabel = new javax.swing.JLabel();
        sendPictureLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendImg();
            }
        });

        chatHistoryLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SendMessageTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setTitle("Chat");
        setBackground(new Color(204, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        rightPanel.setBackground(new Color(204, 255, 255));

        showOtherLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/qqshow/qqshow_girl_02_180.jpg")));

        showSelfLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/qqshow/qqshow_boy_01.jpg")));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(
                rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(rightPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showOtherLabel).addComponent(showSelfLabel));
        rightPanelLayout.setVerticalGroup(rightPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                rightPanelLayout
                        .createSequentialGroup()
                        .addComponent(showOtherLabel)
                        .addGap(18, 18, 18)
                        .addComponent(showSelfLabel,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 251,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)));

        leftPanel.setBackground(new Color(204, 255, 255));

        topPanel.setBackground(new Color(51, 204, 255));

        avatarLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/QQ_64.png")));


        myFriendLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_myfeeds_54.png")));


//        addFriendLabel.setToolTipText("add Friend");


        SendFileLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_sendfile_54.png")));
        SendFileLabel.setToolTipText("Send files");
        SendFileLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                SendFileLabelMouseClicked(evt);
            }
        });

//        addFriendLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_add_54.png")));

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(
                topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout
                .setHorizontalGroup(topPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                topPanelLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(avatarLabel)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SendFileLabel)
                                        .addGap(13, 13, 13)
                                        .addComponent(myFriendLabel)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                23, Short.MAX_VALUE)
//                                        .addComponent(addFriendLabel)
                                        .addContainerGap()));
        topPanelLayout
                .setVerticalGroup(topPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                topPanelLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                topPanelLayout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(avatarLabel)
                                                        .addGroup(
                                                                topPanelLayout
                                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(SendFileLabel)
                                                                        .addComponent(myFriendLabel)
//                                                                        .addComponent(addFriendLabel)
                                                        ))
                                        .addContainerGap(
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)));

        chatPanel.setLayout(new java.awt.BorderLayout());

        receiveTextPane.setEditable(false);
        jScrollPane1.setViewportView(receiveTextPane);

        chatPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        sendPanel.setBackground(new Color(204, 255, 255));

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");

        toolPanel.setBackground(new Color(204, 255, 255));

        fontLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_font_32.png")));
        fontLabel.setToolTipText("font");
        fontLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(
                new Color(204, 255, 255), new Color(204, 255,
                        255)));

        emoticonsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_face_32.png")));
        emoticonsLabel.setToolTipText("emoticons");

        sendPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_picture_32.png")));
        sendPictureLabel.setToolTipText("send picture");


        chatHistoryLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_message_history_32.png")));
        chatHistoryLabel.setText("chat history");
        chatHistoryLabel
                .setToolTipText("Open my chat history");

        javax.swing.GroupLayout toolPanelLayout = new javax.swing.GroupLayout(
                toolPanel);
        toolPanel.setLayout(toolPanelLayout);
        toolPanelLayout
                .setHorizontalGroup(toolPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                toolPanelLayout
                                        .createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(
                                                fontLabel,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(emoticonsLabel)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sendPictureLabel)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                193, Short.MAX_VALUE)
                                        .addComponent(chatHistoryLabel)));
        toolPanelLayout
                .setVerticalGroup(toolPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                toolPanelLayout
                                        .createSequentialGroup()
                                        .addGroup(
                                                toolPanelLayout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(fontLabel)
                                                        .addComponent(emoticonsLabel)
                                                        .addComponent(sendPictureLabel)
                                                        .addComponent(
                                                                chatHistoryLabel))
                                        .addContainerGap()));

        jScrollPane3.setViewportView(SendMessageTextPane);

        javax.swing.GroupLayout sendPanelLayout = new javax.swing.GroupLayout(
                sendPanel);
        sendPanel.setLayout(sendPanelLayout);
        sendPanelLayout
                .setHorizontalGroup(sendPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(toolPanel,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                sendPanelLayout
                                        .createSequentialGroup()
                                        .addContainerGap(321, Short.MAX_VALUE)
                                        .addComponent(
                                                closeButton,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                76,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                sendButton,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                77,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))
                        .addComponent(jScrollPane3,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 486,
                                Short.MAX_VALUE));
        sendPanelLayout
                .setVerticalGroup(sendPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                sendPanelLayout
                                        .createSequentialGroup()
                                        .addComponent(
                                                toolPanel,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                jScrollPane3,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                96,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(
                                                sendPanelLayout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(
                                                                sendButton,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                35,
                                                                Short.MAX_VALUE)
                                                        .addComponent(
                                                                closeButton,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                35,
                                                                Short.MAX_VALUE))
                                        .addContainerGap()));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(
                leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(leftPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        486, Short.MAX_VALUE));
        leftPanelLayout
                .setVerticalGroup(leftPanelLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                leftPanelLayout
                                        .createSequentialGroup()
                                        .addComponent(
                                                topPanel,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                89,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                chatPanel,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                260, Short.MAX_VALUE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                sendPanel,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addComponent(leftPanel,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightPanel,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leftPanel,
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }


    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        beforeClose();
    }

    private void SendFileLabelMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        sendFile();
    }

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        sendMessage();
    }

    private javax.swing.JButton closeButton;
    public javax.swing.JButton sendButton;
    private javax.swing.JLabel showOtherLabel;
    private javax.swing.JLabel showSelfLabel;

    public javax.swing.JLabel SendFileLabel;
    public javax.swing.JLabel sendPictureLabel;
    private javax.swing.JLabel avatarLabel;
    public javax.swing.JLabel fontLabel;
    public javax.swing.JLabel myFriendLabel;

    public javax.swing.JLabel chatHistoryLabel;
    public javax.swing.JLabel emoticonsLabel;

    private javax.swing.JPanel sendPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextPane SendMessageTextPane;
    public javax.swing.JTextPane receiveTextPane;


    public javax.swing.JTextPane getReceiveTextPane() {
        return receiveTextPane;
    }

    public void setReceiveTextPane(javax.swing.JTextPane receiveTextPane) {
        this.receiveTextPane = receiveTextPane;
    }

    /**
     * Send a message, an empty method, implemented by a subclass
     */
    public void sendMessage() {
    }

    public void beforeClose() {
    }

    /**
     * Select an emote
     */
    public void selectFace() {
    }

    /**
     * deal emoticons in messages
     */
    public void dealIcon(String str) {

    }

    /**
     * send file
     */
    public void sendFile() {

    }


    /**
     * send picture
     */
    public void sendImg() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}