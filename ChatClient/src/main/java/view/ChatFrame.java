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


}