package interface_adapter.signUp;

import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInputData;

public class SignUpController {

    final SignUpInputBoundary userSignupUseCaseInteractor;
    public SignUpController(SignUpInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) {
        SignUpInputData signupInputData = new SignUpInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
