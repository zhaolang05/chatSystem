package use_case.forgot;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class ForgotInteractor implements ForgotInputBoundary {
    final ForgotUserDataAccessInterface userDataAccessObject;
    final ForgotOutputBoundary userPresenter;
    final UserFactory userFactory;

    public ForgotInteractor(ForgotUserDataAccessInterface forgotDataAccessInterface,
                            ForgotOutputBoundary forgotOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = forgotDataAccessInterface;
        this.userPresenter = forgotOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void back(String username) {
        userPresenter.prepareLoginView(username);
    }

    @Override
    public void execute(ForgotInputData forgotInputData) {
        if (!userDataAccessObject.existsByName(forgotInputData.getUsername())) {
            userPresenter.prepareFailView("User doesn't exists.");
        } else if (!forgotInputData.getPassword().equals(forgotInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            LocalDateTime now = LocalDateTime.now();
            User oldUser = userDataAccessObject.get(forgotInputData.getUsername());
            User user = userFactory.create(forgotInputData.getUsername(), forgotInputData.getPassword(), oldUser.getCreationTime());
            userDataAccessObject.save(user);

            ForgotOutputData forgotOutputData = new ForgotOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(forgotOutputData);
        }
    }
}