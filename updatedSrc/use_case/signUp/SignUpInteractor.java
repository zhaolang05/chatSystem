package use_case.signUp;
import entity.CommonUser;

import java.util.Random;

public class SignUpInteractor implements SignUpInputBoundary {
    final SignUpUserDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary userPresenter;

    public SignUpInteractor(SignUpUserDataAccessInterface userDataAccessObject, SignUpOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        if (userDataAccessObject.existsByName(signUpInputData.getUserName())) {
            userPresenter.prepareFailView("Username already exists");
        } else if (!signUpInputData.getAccountPassword().equals(signUpInputData.getRepeatAccountPassword())) {
            userPresenter.prepareFailView("Passwords do not match");
        } else {
            CommonUser user = new CommonUser(signUpInputData.getUserName(), signUpInputData.getAccountPassword());
            userDataAccessObject.save(user);

            SignUpOutputData outputData = new SignUpOutputData(user.getUserName());
            userPresenter.prepareSuccessView(outputData);
        }
    }

}



