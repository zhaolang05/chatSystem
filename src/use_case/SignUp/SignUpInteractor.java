package use_case.SignUp;
import entity.CommonUser;

public class SignUpInteractor implements SignUpInputBoundary {
    final SignUpUserDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary userPresenter;

    public SignUpInteractor(SignUpUserDataAccessInterface userDataAccessObject, SignUpOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        if (userDataAccessObject.existByName(signUpInputData.getUserName())){
            userPresenter.prepareFailView("Username already exists");
        }
        else if (!signUpInputData.getAccountPassword().equals(signUpInputData.getRepeatAccountPassword())){
            userPresenter.prepareFailView("Passwords do not match");
        }
        else{

        CommonUser user = new CommonUser(signUpInputData.getUserName(), signUpInputData.getAccountPassword());
        userDataAccessObject.save(user);

    }
}}
