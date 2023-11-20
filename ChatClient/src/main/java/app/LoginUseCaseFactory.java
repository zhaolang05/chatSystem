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

import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private LoginUseCaseFactory() {
    }

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,SignupViewModel signupViewModel) {
        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,signupViewModel);
        return new LoginView(loginViewModel, loginController);

    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel,
                                                      LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, SignupViewModel signupViewModel) {
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel,signupViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary);
        return new LoginController(loginInteractor);
    }
}
