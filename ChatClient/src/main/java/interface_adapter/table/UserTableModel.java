package interface_adapter.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import comm.entity.User;

public class UserTableModel extends AbstractTableModel {

    String[] columnNames = new String[]{"name", "profile", "online", "personalized_sign"};


    public List<User> userList = new ArrayList<>();


    public int getRowCount() {
        return userList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getName();
            case 1:
                return user.getProfile();
            case 2:
                if (user.getOnline()==null)
                {
                    return "No";
                }
                else {
                    if (user.getOnline() == 1) {
                        return "Yes";
                    } else {
                        return "No";
                    }
                }
            case 3:
                return user.getPersonalizedSign();
            default:
                return null;
        }

    }

}