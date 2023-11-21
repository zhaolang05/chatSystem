package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demoview {

    public String viewName = "demo";
    private JPanel titlePanel;
    private JPanel mainPanel;
    private JButton testbotton;

    public Demoview() {
        testbotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
