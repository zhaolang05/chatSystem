package view;

import interface_adapter.changeUsername.ChangeUsernameController;
import interface_adapter.changeUsername.ChangeUsernameState;
import interface_adapter.changeUsername.ChangeUsernameViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.changeUsername.ChangeUsernameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.signUp.SignUpState;
import use_case.changeUsername.ChangeUsernameInputData;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final LoginViewModel loginViewModel;

    private final ChangeUsernameController changeUsernameController;

    private ChangeUsernameViewModel changeUsernameViewModel;

    JLabel username;

    private final JButton changeUsername;

    private final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                        ChangeUsernameViewModel changeUsernameViewModel, ChangeUsernameController changeUsernameController) {
        this.loggedInViewModel = loggedInViewModel;
        this.changeUsernameController = changeUsernameController;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        changeUsername = new JButton(changeUsernameViewModel.CHANGEUSERNAME_BUTTON_LABEL);

        buttons.add(changeUsername);

        logOut.addActionListener(this);

        changeUsernameViewModel.addPropertyChangeListener(this);

        loggedInViewModel.addPropertyChangeListener(this);

        changeUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(changeUsername)){
               String newName = JOptionPane.showInputDialog("Pick a new name");
               changeUsernameController.execute(newName);

            }
        }});

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);

    }


    public void actionPerformed(ActionEvent evt) {
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue() instanceof ChangeUsernameState);
        if (evt.getNewValue() instanceof ChangeUsernameState) {
            ChangeUsernameState state = (ChangeUsernameState) evt.getNewValue();
            if (state.getUsernameError() != null){
                JOptionPane.showMessageDialog(this, state.getUsernameError().concat(". Please try again") );}
            else {
                JOptionPane.showMessageDialog(this," Username changed to " + state.getNewUsername());
            }
            state.flush();
        }
        else {
                LoggedInState state = (LoggedInState) evt.getNewValue();
                username.setText(state.getUsername());

            }
        }
    }
