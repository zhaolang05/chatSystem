package view;

import comm.entity.User;
import view.comm.MyLabel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

/**
 *
 */
public class MainFrame extends JFrame {
    public JLabel settingLabel;
    public JLabel avatarLabel;
    public JComboBox<ImageIcon> statusComboBox;
    public JLabel userNameLabel;
    public JLabel tagLabel;
    public JTree tree;
    private JTabbedPane tabbedPane;
    private JPanel friendsListPanel;
    private JPanel groupPanel;

    private JScrollPane friendsScrollPane;
    private JPanel userInfoPanel;
    private JPanel friendsPanel;
    private JPanel settingPanel;
    private JPopupMenu friendsListPopupMenu;
    private JPopupMenu settingPopupMenu;
    private JMenuItem sendMessageMenuItem;
    private JMenuItem viewFriendInfoMenuItem;
    private JMenuItem sendFileMenuItem;
    private JMenuItem deleteUserMenuItem;
    private JLabel addFriendLabel;




    /**
     * Create the frame.
     */
    public MainFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/img/QQ_64.png")));
        setTitle("My Chat System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 600);

        userInfoPanel = new JPanel();
        userInfoPanel.setBackground(new Color(51, 153, 255));

        friendsPanel = new JPanel();


        settingPanel = new JPanel();
        settingPanel.setBackground(new Color(51, 153, 255));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userInfoPanel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                        .addComponent(settingPanel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                        .addComponent(friendsPanel, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(userInfoPanel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(friendsPanel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(settingPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
        );
        friendsPanel.setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        friendsPanel.add(tabbedPane, BorderLayout.CENTER);

        friendsListPanel = new JPanel();
        tabbedPane.addTab("My Friends", new ImageIcon(MainFrame.class.getResource("/img/friend_list.png")), friendsListPanel, null);
        friendsListPanel.setLayout(new BorderLayout(0, 0));

        friendsScrollPane = new JScrollPane();
        friendsListPanel.add(friendsScrollPane);

        tree = new JTree();
        friendsScrollPane.setViewportView(tree);

        friendsListPopupMenu = new JPopupMenu();
        addPopup(tree, friendsListPopupMenu);

        sendMessageMenuItem = new JMenuItem("Send instant messages");
        sendMessageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startChat(e);
            }
        });
        sendMessageMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/img/QQ_16.png")));
        friendsListPopupMenu.add(sendMessageMenuItem);

        sendFileMenuItem = new JMenuItem("send file");
        sendFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendFile(e);
            }
        });
        friendsListPopupMenu.add(sendFileMenuItem);

        viewFriendInfoMenuItem = new JMenuItem("view friend info");
        viewFriendInfoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getFriendInfo(e);
            }
        });
        friendsListPopupMenu.add(viewFriendInfoMenuItem);

        deleteUserMenuItem = new JMenuItem("delete friend");
        deleteUserMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFriend(e);
            }
        });
        friendsListPopupMenu.add(deleteUserMenuItem);

        groupPanel = new JPanel();
        tabbedPane.addTab("Groop CHat", new ImageIcon(MainFrame.class.getResource("/img/friend_qun.png")), groupPanel, null);

        JButton officialChatRoomButton = new JButton("Official chat room");
        officialChatRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gotoChatRoom();
            }
        });
        officialChatRoomButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        JButton createNewChatRoomButton = new JButton("Create a new chat room");
        createNewChatRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buildNewChatRoom();
            }
        });
        createNewChatRoomButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        GroupLayout GroupPanelLayout = new GroupLayout(groupPanel);
        GroupPanelLayout.setHorizontalGroup(
                GroupPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, GroupPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(GroupPanelLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(createNewChatRoomButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                        .addComponent(officialChatRoomButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                                .addContainerGap())
        );
        GroupPanelLayout.setVerticalGroup(
                GroupPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(GroupPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(officialChatRoomButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(createNewChatRoomButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(132, Short.MAX_VALUE))
        );
        groupPanel.setLayout(GroupPanelLayout);


        settingPanel.setLayout(null);

        settingLabel = new JLabel("");
        settingLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/img/button/QQ_settings_1.png")));
        settingLabel.setBounds(6, 6, 64, 64);
        settingPanel.add(settingLabel);
        userInfoPanel.setLayout(null);

        addFriendLabel = new JLabel();
        addFriendLabel.setToolTipText("add Friend");
        addFriendLabel.setBounds(76, 6, 64, 64);
        addFriendLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chat/fun_add_54.png")));
        settingPanel.add(addFriendLabel);

        settingPopupMenu = new JPopupMenu();


        settingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        exitMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/img/exit.png")));
        settingPopupMenu.add(exitMenuItem);


        JMenuItem changPasswordMenuItem = new JMenuItem("Change password");
        changPasswordMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePassword(e);
            }
        });
        changPasswordMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/img/key.png")));
        settingPopupMenu.add(changPasswordMenuItem);



        JMenuItem changProfileMenuItem = new JMenuItem("Change Profile");
        changProfileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeProfile(e);
            }
        });
        changProfileMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/img/profile.png")));
        settingPopupMenu.add(changProfileMenuItem);



        JMenuItem changUserNameMenuItem = new JMenuItem("Change user Name");
        changUserNameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeUserName(e);
            }
        });
        changUserNameMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/img/user.png")));
        settingPopupMenu.add(changUserNameMenuItem);


        avatarLabel = new JLabel("");
        avatarLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/img/headImage/head_boy_01_64.jpg")));
        avatarLabel.setBounds(15, 15, 64, 64);
        userInfoPanel.add(avatarLabel);

        statusComboBox = new JComboBox<ImageIcon>();
        statusComboBox.setBounds(91, 15, 58, 28);
        userInfoPanel.add(statusComboBox);

        userNameLabel = new JLabel("\u9A6C\u5316\u817E");
        userNameLabel.setFont(new Font("黑体", Font.BOLD, 18));
        userNameLabel.setBounds(159, 18, 90, 25);
        userInfoPanel.add(userNameLabel);

        tagLabel = new JLabel("personalized Sign");
        tagLabel.setBounds(91, 55, 210, 18);
        userInfoPanel.add(tagLabel);
        getContentPane().setLayout(groupLayout);
        new MyLabel(addFriendLabel).addEvent();
        addFriendLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addFriend();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    /**
     * change Password
     */
    public void changePassword(ActionEvent e) {
    }

    public void changeUserName(ActionEvent e) {
    }

    public void addFriend() {
    }


    public void changeProfile(ActionEvent e) {

    }
    /**
     * Start chatting
     */
    public void startChat(ActionEvent e) {
    }

    /**
     * Get a friend's profile
     *
     * @param e
     */
    public void getFriendInfo(ActionEvent e) {

    }

    /**
     * Send the file
     *
     * @param e
     */
    public void sendFile(ActionEvent e) {

    }

    /**
     * Delete a friend
     *
     * @param e
     */
    public void deleteFriend(ActionEvent e) {

    }

    /**
     * Enter the chat room
     */
    public void gotoChatRoom() {
    }

    /**
     * Create a new chat room
     */
    public void buildNewChatRoom() {

    }
}
