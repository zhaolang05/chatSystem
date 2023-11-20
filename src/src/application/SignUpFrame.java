package application;

import javax.swing.*;
import java.awt.*;

public class SignUpFrame extends JFrame{

    public SignUpFrame(){
//        ImageIcon image = new ImageIcon("handsome.png");
//        JLabel label = new JLabel();
//        label.setText("Lady, please sign up");
//        label.setIcon(image);
//        label.setHorizontalTextPosition(JLabel.BOTTOM);
//
//        this.add(label);

        this.setTitle("Chatting System");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(420,420);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(204,255,255));}
}
