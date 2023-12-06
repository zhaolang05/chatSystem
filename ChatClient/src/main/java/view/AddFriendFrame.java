/*
 * ChatFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import comm.constant.MessageType;
import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.Event;
import comm.event.EventListener;
import interface_adapter.table.UserTableModel;
import interface_adapter.chat.ChatController;
import view.comm.MyTools;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AddFriendFrame extends JFrame implements EventListener {

    /**
     * Creates new form ChangeUserNameFrame
     */
    final private User user;
    final private ChatController controller;
    private JButton addFriendButton;
    private JTextField usernameInputField;
    private JTextField profileInputField;
    private JTable friendTable;
    private JButton searchButton;
    private JButton clearButton;
    private UserTableModel userTableModel;

    private List<User> users;

    public AddFriendFrame(ChatController controller, List<User> users, User user) {
        this.controller = controller;
        this.controller.registerListener(this);
        this.user = user;
        this.users = users;
        initComponents(users);
        requestList();

    }

    private void requestList()
    {
        ChatMessage chatMessage=new ChatMessage();
        chatMessage.setMessageType(MessageType.GET_USERS);
        controller.sendMessage(chatMessage);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(java.util.List<User> users) {
        this.setTitle("Add Friend");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 50, 400);
        this.setSize(50, 400);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(new Color(255, 255, 255));

        usernameInputField = new JTextField(10);


        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("User Name"), usernameInputField);

        searchInfo.add(new JLabel("profile"));

        profileInputField = new JTextField(21);
        searchInfo.add(profileInputField);

        JPanel searchbuttons = new JPanel();
        searchButton = new JButton("Search");
        searchButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            searchFriend();
                        }
                    }
                }
        );
        clearButton = new JButton("Clear");
        clearButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clearButton)) {
                            usernameInputField.setText("");
                            profileInputField.setText("");
                        }
                    }
                }
        );

        searchbuttons.add(searchButton);
        searchbuttons.add(clearButton);
        searchInfo.add(searchbuttons);
        searchInfo.setLayout(new BoxLayout(searchInfo, BoxLayout.X_AXIS));
        searchInfo.setSize(50, 8);
        this.add(searchInfo, BorderLayout.PAGE_START);


        userTableModel = new UserTableModel();
        userTableModel.userList = users;
        friendTable = new JTable(userTableModel);
        FitTableColumns(friendTable);
        JScrollPane sp = new JScrollPane(friendTable);
        this.add(sp, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        addFriendButton = new JButton("Add friend");
        buttons.add(addFriendButton);
        JButton cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);


        addFriendButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addFriendButton)) {
                            addFriend();
                        }
                    }
                }
        );

        this.add(buttons, BorderLayout.PAGE_END);
        MyTools.setWindowsMiddleShow(this, 50, 400);
        pack();
    }


    @Override
    public void handleEvent(Event event) {
        String message = event.getData().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        switch(chatMessage.getMessageType())
        {
            case MessageType.ADD_FRIEND:
                if (chatMessage.getCode().equals("1")) {
                    JOptionPane.showMessageDialog(this, "add friend success");
                } else if (chatMessage.getCode().equals("2")) {
                    JOptionPane.showMessageDialog(this, "add friend request send success");
                } else if (chatMessage.getCode().equals("3")) {
                    JOptionPane.showMessageDialog(this, "user reject add friend request");
                }
                break;
            case MessageType.GET_USERS:
                List<User> users=gson.fromJson(gson.toJson(chatMessage.getData().get("users")),new TypeToken<List<User>>(){}.getType());
                this.users=users;
                userTableModel.userList = users;
                userTableModel.fireTableDataChanged();
                FitTableColumns(friendTable);
                break;


        }


    }


    /***
     * Set the column width of the table to adjust according to the content
     * @param myTable
     */
     public void FitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable,
                            column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++){
                int preferedWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable,
                                myTable.getValueAt(row, col), false, false,
                                row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }

    private void searchFriend() {
        List<User> users1 = users.stream().filter(User-> !User.getName().isEmpty()).collect(Collectors.toList());;;
        if (!usernameInputField.getText().isEmpty()) {
            String searchUsername = usernameInputField.getText();
            users1 = users1.stream().filter(User -> User.getName().indexOf(searchUsername) >= 0).collect(Collectors.toList());
        }
        if (!profileInputField.getText().isEmpty()) {
            String searchProfile = profileInputField.getText();
            users1 = users1.stream().filter(User -> User.getName().indexOf(searchProfile) >= 0).collect(Collectors.toList());
        }

        userTableModel.userList = users1;
        userTableModel.fireTableDataChanged();
    }

    private void addFriend() {
        int selectedRow = friendTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a user before click Add Friends");
            return;
        }
        String userName = friendTable.getValueAt(selectedRow, 0).toString();
        ChatMessage message = new ChatMessage();
        message.setMessageType(MessageType.ADD_FRIEND);
        Map<String, Object> data = new HashMap<>();
        data.put("fromuser", user.getName());
        data.put("username", userName);
        message.setData(data);
        controller.sendMessage(message);
    }
}