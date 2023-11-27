package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void forgot(String username) {
        loginUseCaseInteractor.forgot(username);
    }

    public void back(){ this.loginUseCaseInteractor.back();}


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
