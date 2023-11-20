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
        if (userDataAccessObject.existByName(signUpInputData.getUserName())) {
            userPresenter.prepareFailView("Username already exists");
        } else if (!signUpInputData.getAccountPassword().equals(signUpInputData.getRepeatAccountPassword())) {
            userPresenter.prepareFailView("Passwords do not match");
        } else {
            int[] userID = SignUpInteractor.userIdGenerator();
            while (userDataAccessObject.existID(userID)) {
                userID = SignUpInteractor.userIdGenerator();
            }
            CommonUser user = new CommonUser(signUpInputData.getUserName(), signUpInputData.getAccountPassword(), userID);
            userDataAccessObject.save(user);
        }
    }
    public static int[] userIdGenerator() {
        int[] userID = new int[9];
        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            int rand = random.nextInt(1,10);
            userID[i] = rand;

        }

        return userID;
    }
}



