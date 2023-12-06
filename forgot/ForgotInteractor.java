package use_case.forgot;

import entity.CommonUser;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class ForgotInteractor implements ForgotInputBoundary {
    final ForgotUserDataAccessInterface userDataAccessObject;
    final ForgotOutputBoundary userPresenter;

    public ForgotInteractor(ForgotUserDataAccessInterface forgotDataAccessInterface,
                            ForgotOutputBoundary forgotOutputBoundary) {
        this.userDataAccessObject = forgotDataAccessInterface;
        this.userPresenter = forgotOutputBoundary;
    }

    @Override
    public void back(String username) {
        userPresenter.prepareLoginView(username);
    }

    @Override
    public void execute(ForgotInputData forgotInputData) {
        if (!userDataAccessObject.existsByName(forgotInputData.getUsername())) {
            userPresenter.prepareFailView("User doesn't exist.");
        } else if (!forgotInputData.getPassword().equals(forgotInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            LocalDateTime now = LocalDateTime.now();
            CommonUser user = new CommonUser(forgotInputData.getUsername(), forgotInputData.getPassword());
            userDataAccessObject.save(user);

            ForgotOutputData forgotOutputData = new ForgotOutputData(user.getUserName(), now.toString(), false);
            userPresenter.prepareSuccessView(forgotOutputData);
        }
    }
}