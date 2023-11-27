package application;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.changeUsername.ChangeUsernameViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.forgot.ForgotViewModel;
import interface_adapter.signUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import view.ForgotView;
import view.LoggedInView;
import view.LoginView;
import view.SignUpView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        ForgotViewModel forgotViewModel = new ForgotViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        ChangeUsernameViewModel changeUsernameViewModel = new ChangeUsernameViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignUpView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signUpViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, forgotViewModel, signUpViewModel,
                loggedInViewModel,
                 userDataAccessObject);
        views.add(loginView, loginView.viewName);

        ForgotView forgotView = ForgotUseCaseFactory.create(viewManagerModel, loginViewModel, forgotViewModel, userDataAccessObject);
        views.add(forgotView, forgotView.viewName);

        LoggedInView loggedInView = LoggedInViewFactory.create(loggedInViewModel, viewManagerModel, loginViewModel, userDataAccessObject,
                changeUsernameViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}