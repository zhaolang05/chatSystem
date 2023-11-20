package use_case.changeUsername;

import entity.CommonUser;

public class ChangeUsernameInteractor implements ChangeUsernameInputBoundary {
    private ChangeUsernameDataAccessInterface dataAccessObject;
    private ChangeUsernameOutputBoundary presenter;
    public ChangeUsernameInteractor(ChangeUsernameDataAccessInterface dataAccessObject,
                                    ChangeUsernameOutputBoundary presenter){
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(ChangeUsernameInputData inputData) {
        CommonUser user = dataAccessObject.getLoggedinUser(inputData.getUserID());
        user.setUserName(inputData.getNewUsername());
        ChangeUsernameOutputData outputData = new ChangeUsernameOutputData(user.getUserName());
        presenter.prepareSuccessView(outputData);

    }
}
