package interface_adapter.signUp;

import use_case.SignUp.SignUpInputData;
import use_case.SignUp.SignUpInteractor;

public class SignUpController {
    private SignUpInteractor signUpInteractor;

    public SignUpController (SignUpInteractor signUpInteractor){
        this.signUpInteractor = signUpInteractor;
    }

    public void execute( String userName, String accountPassword, String repeatAccountPassword){
        SignUpInputData signUpInputData= new SignUpInputData(userName, accountPassword, repeatAccountPassword);
        signUpInteractor.execute(signUpInputData);

    }
}
