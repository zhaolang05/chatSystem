package interface_adapter.changepassword;

import use_case.changepassword.ChangePasswordInputBoundary;
import use_case.changepassword.ChangePasswordInputData;

public class ChangePasswordController {
    final ChangePasswordInputBoundary forgotUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary forgotUseCaseInteractor) {
        this.forgotUseCaseInteractor = forgotUseCaseInteractor;
    }

    public void execute(String username, String password){
        ChangePasswordInputData forgotInputData = new ChangePasswordInputData(username, password);
        forgotUseCaseInteractor.execute(forgotInputData);

    }
}
