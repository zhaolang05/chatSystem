package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import java.net.URISyntaxException;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) throws URISyntaxException, InterruptedException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    public void toSignup()  {
        loginUseCaseInteractor.toSignup();
    }
}
