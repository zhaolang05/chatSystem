package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2,String profile,String personalizedSign,String avtar) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2,profile,personalizedSign,avtar);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
    public void toLogin()
    {
        userSignupUseCaseInteractor.toLogin();
    }
}
