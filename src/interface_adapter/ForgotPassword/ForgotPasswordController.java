package interface_adapter.ForgotPassword;

import use_case.ForgotPassword.ForgotPasswordInputBoundary;
import use_case.ForgotPassword.ForgotPasswordInputData;

public class ForgotPasswordController {
    final ForgotPasswordInputBoundary forgotPasswordUseCaseInteractor;


    public ForgotPasswordController(ForgotPasswordInputBoundary forgotPasswordUseCaseInteractor) {
        this.forgotPasswordUseCaseInteractor = forgotPasswordUseCaseInteractor;
    }

    void execute(String username, String newPassword){
        ForgotPasswordInputData forgotPasswordInputData = new ForgotPasswordInputData(username, newPassword);
        forgotPasswordUseCaseInteractor.execute(forgotPasswordInputData);
    }
}
