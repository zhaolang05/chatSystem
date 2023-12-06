package interface_adapter.forgot;

import use_case.forgot.ForgotInputData;
import use_case.forgot.ForgotInputBoundary;

public class ForgotController {

    final ForgotInputBoundary forgotUseCaseInteractor;
    public ForgotController(ForgotInputBoundary forgotUseCaseInteractor) {
        this.forgotUseCaseInteractor = forgotUseCaseInteractor;
    }

    public void back(String username) {
        this.forgotUseCaseInteractor.back(username);
    }


    public void execute(String username, String password, String repeatPassword) {
        ForgotInputData forgotInputData = new ForgotInputData(
                username, password, repeatPassword);

        forgotUseCaseInteractor.execute(forgotInputData);
    }
}
