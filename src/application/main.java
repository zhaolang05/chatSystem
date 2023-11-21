package application;
import data_access.FileUserDataAccessObject;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import view.SignUpView;
import view.ViewManager;
import view.LoginView;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import interface_adapter.signUp.SignUpViewModel;

public class main {
    public static void main(String[] args) throws IOException {

        JFrame application = new SignUpFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);

        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();

        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();

        SignUpViewModel signupViewModel = new SignUpViewModel();

        FileUserDataAccessObject userDataAccessObject;

        userDataAccessObject = new FileUserDataAccessObject();
            //clearDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        SignUpView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);



//    Change
//    ChangeUsernameInputBoundary interactor = new ChangeUsernameInteractor();
//    ChangeUsernameController changeUsernameController = new ChangeUsernameController()
//    ChangeUsernameView view1 = new ChangeUsernameView();

}}
