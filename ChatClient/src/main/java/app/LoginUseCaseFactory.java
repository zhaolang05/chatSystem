package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;

import view.ChatMainView;
import view.LoginView;

import javax.swing.*;


public class LoginUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private LoginUseCaseFactory() {
    }

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel, SignupViewModel signupViewModel, ChatMainView chatMainView, JFrame application) {
        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,signupViewModel,chatMainView,application);
        return new LoginView(loginViewModel, loginController);

    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel,
                                                      LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, SignupViewModel signupViewModel, ChatMainView chatMainView,JFrame application) {
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel,signupViewModel,chatMainView,application);
        LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary);
        return new LoginController(loginInteractor);
    }
}
