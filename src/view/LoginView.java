package view;

import interface_adapter.Login.LoginController;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginView implements LoginOutputBoundary {
    private LoginController loginController;

    public LoginView(){
        this.loginController =
    }
    @Override
    public void prepareSuccessView(LoginOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
