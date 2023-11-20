package interface_adapter.changeUsername;

import use_case.changeUsername.ChangeUsernameInputBoundary;
import use_case.changeUsername.ChangeUsernameInputData;
import use_case.changeUsername.ChangeUsernameInteractor;

public class ChangeUsernameController {
    private ChangeUsernameInputBoundary changeUsernameInteractor;

    public ChangeUsernameController(ChangeUsernameInputBoundary changeUsernameInteractor){
        this.changeUsernameInteractor = changeUsernameInteractor;
    }
    public void execute(String newUsername, int[] userID) {
        ChangeUsernameInputData inputData = new ChangeUsernameInputData(newUsername, userID);
        changeUsernameInteractor.execute(inputData);
    }
}
