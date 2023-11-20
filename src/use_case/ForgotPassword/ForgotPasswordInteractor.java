package use_case.ForgotPassword;

import use_case.ForgotPassword.ForgotPasswordOutputData;
import entity.User;
import interface_adapter.ForgotPassword.ForgotPasswordPresenter;

public class ForgotPasswordInteractor implements ForgotPasswordInputBoundary{
    private final ForgotPasswordUserDataAccessInterface forgotPasswordUserDataAccessInterface;
    private final ForgotPasswordOutputBoundary forgotPasswordPresenter;

    public ForgotPasswordInteractor(ForgotPasswordUserDataAccessInterface forgotPasswordUserDataAccessInterface,
                                    ForgotPasswordOutputBoundary forgotPasswordPresenter) {
        this.forgotPasswordUserDataAccessInterface = forgotPasswordUserDataAccessInterface;

        this.forgotPasswordPresenter = forgotPasswordPresenter;
    }

    @Override
    public void execute(ForgotPasswordInputData forgotPasswordInputData) {
        String username = forgotPasswordInputData.getUsername();
        if(!forgotPasswordUserDataAccessInterface.existByName(username)){
            ForgotPasswordPresenter.prepareFailView("Couldn't find " + username + ". Try again.");
        }
        else{
            User user = forgotPasswordUserDataAccessInterface.getUser(username);
            String resetPassword = forgotPasswordInputData.getPassWord();
            ForgotPasswordOutputData forgotPasswordOutputData = new ForgotPasswordOutputData(user.getUsername(),
                    true);
            forgotPasswordPresenter.prepareSuccessView(forgotPasswordOutputData);

        }
    }
}
