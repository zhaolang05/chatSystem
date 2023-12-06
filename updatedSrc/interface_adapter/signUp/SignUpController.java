package interface_adapter.signUp;

import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInputData;
import use_case.signUp.SignUpInteractor;

public class SignUpController {
    private SignUpInputBoundary signUpInteractor;

    public SignUpController (SignUpInputBoundary signUpInteractor){
        this.signUpInteractor = signUpInteractor;
    }

    public void execute( String userName, String accountPassword, String repeatAccountPassword){
        SignUpInputData signUpInputData= new SignUpInputData(userName, accountPassword, repeatAccountPassword);
        signUpInteractor.execute(signUpInputData);

    }
}
