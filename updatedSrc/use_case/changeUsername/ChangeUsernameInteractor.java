package use_case.changeUsername;

import entity.CommonUser;
import interface_adapter.logged_in.LoggedInViewModel;

public class ChangeUsernameInteractor implements ChangeUsernameInputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ChangeUsernameDataAccessInterface dataAccessObject;
    private ChangeUsernameOutputBoundary presenter;
    public ChangeUsernameInteractor(ChangeUsernameDataAccessInterface dataAccessObject,
                                    ChangeUsernameOutputBoundary presenter, LoggedInViewModel loggedInViewModel){
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void execute(ChangeUsernameInputData inputData) {

        if (dataAccessObject.existsByName(inputData.getNewUsername())) {
            presenter.prepareFailView("Username already exists");}
            else{
        CommonUser user = dataAccessObject.get(loggedInViewModel.getState().getUsername());
        user.setUserName(inputData.getNewUsername());
        dataAccessObject.save(user);
        ChangeUsernameOutputData outputData = new ChangeUsernameOutputData(user.getUserName());
        presenter.prepareSuccessView(outputData);}

    }
}
