package view;
import interface_adapter.changeUsername.ChangeUsernameController;
import interface_adapter.changeUsername.ChangeUsernameState;
import interface_adapter.changeUsername.ChangeUsernameViewModel;
import interface_adapter.signUp.SignUpState;
import use_case.changeUsername.ChangeUsernameInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ChangeUsernameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Change Username";

    private final JTextField changeUsernameInputField = new JTextField(15);

    private final ChangeUsernameViewModel changeUsernameViewModel;

    private final ChangeUsernameController changeUsernameController;

    private final JButton changeUsername;

    public ChangeUsernameView(ChangeUsernameViewModel changeUsernameViewModel, ChangeUsernameController changeUsernameController) {
        this.changeUsernameViewModel = changeUsernameViewModel;
        this.changeUsernameController = changeUsernameController;
        JLabel title = new JLabel(ChangeUsernameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel newUsernameInfo = new LabelTextPanel(
                new JLabel(ChangeUsernameViewModel.USERNAME_LABEL), changeUsernameInputField);

        JPanel buttons = new JPanel();
        this.changeUsername = new JButton(ChangeUsernameViewModel.CHANGEUSERNAME_BUTTON_LABEL);
        buttons.add(changeUsername);

        changeUsername.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(changeUsername)) {
            ChangeUsernameState currentState = changeUsernameViewModel.getState();

            changeUsernameController.execute(
                    currentState.getNewUsername(), currentState.getUserID());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof ChangeUsernameState) {
            ChangeUsernameState state = (ChangeUsernameState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, "Username changed to" + state.getNewUsername());


        }
    }
}

