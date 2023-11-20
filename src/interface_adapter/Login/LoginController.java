package interface_adapter.Login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor){
        this.loginInteractor = loginInteractor;
    }

    public void login(String username, String password){
        LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.login(loginInputData);
    }
}
