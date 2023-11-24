package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordView extends JPanel {

    public ChangePasswordView() {
        // Set up the layout manager
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, with gaps

        // Create and add components

        add(new JLabel("New Password:"));
        JPasswordField newPasswordField = new JPasswordField(10);
        add(newPasswordField);

        add(new JLabel("Confirm New Password:"));
        JPasswordField confirmPasswordField = new JPasswordField(10);
        add(confirmPasswordField);

        JButton changePasswordButton = new JButton("Done");
        JButton CancelButton = new JButton("Cancel");

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("cancel clicked");
            }
        });
        add(CancelButton);
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to change password
                // You should verify the old password and check if the new passwords match
                // This is just a simple print for demonstration
                System.out.println("Change password clicked");
            }
        });

        add(changePasswordButton);

    }

    // Test the ChangePasswordView panel
    public static void main(String[] args) {
        // Create a frame to hold the panel
        JFrame frame = new JFrame("Change Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create an instance of ChangePasswordView and add it to the frame
        ChangePasswordView changePasswordView = new ChangePasswordView();
        frame.add(changePasswordView);

        // Display the frame
        frame.setVisible(true);
    }
}
