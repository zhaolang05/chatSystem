package use_case.changepassword;

import entity.CommonUser;

public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface forgotUserDataAccessObject;
    private final ChangePasswordOutPutBoundary forgotPresenter;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface forgotUserDataAccessObject, ChangePasswordOutPutBoundary forgotPresenter) {
        this.forgotUserDataAccessObject = forgotUserDataAccessObject;
        this.forgotPresenter = forgotPresenter;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        String username = changePasswordInputData.getUsername();
        if(!forgotUserDataAccessObject.existsByName(username)){
            forgotPresenter.prepareFailView("Couldn't find " + username + ". Try again?");
        }
        else{
            CommonUser user = forgotUserDataAccessObject.getUser(username);
            String resetPassword = changePasswordInputData.getPassword();
            user.setAccountPassword(resetPassword);
            ChangePasswordOutputData forgotPasswordOutputData = new ChangePasswordOutputData(user.getUserName(),
                    true);
            forgotPresenter.prepareSuccessView(forgotPasswordOutputData);
        }

    }
}
