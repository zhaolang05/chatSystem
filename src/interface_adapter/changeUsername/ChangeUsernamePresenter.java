package interface_adapter.changeUsername;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signUp.SignUpState;
import use_case.changeUsername.ChangeUsernameOutputBoundary;
import use_case.changeUsername.ChangeUsernameOutputData;

public class ChangeUsernamePresenter implements ChangeUsernameOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private LoggedInViewModel loggedInViewModel;

    private LoginViewModel loginViewModel;

    private ChangeUsernameViewModel changeUsernameViewModel;

    public ChangeUsernamePresenter(ChangeUsernameViewModel changeUsernameViewModel, ViewManagerModel viewManagerModel,
                                   LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.changeUsernameViewModel = changeUsernameViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeUsernameOutputData outputData) {
        LoggedInState loggedInstate = loggedInViewModel.getState();
        loggedInstate.setUsername(outputData.getNewUsername());
        LoginState loginstate = loginViewModel.getState();
        loginstate.setUsername(outputData.getNewUsername());
        this.loginViewModel.setState(loginstate);
        this.loginViewModel.firePropertyChanged();
        this.loggedInViewModel.setState(loggedInstate);
        this.loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
    @Override
    public void prepareFailView(String errorMessage){
        ChangeUsernameState changeUsernameState= changeUsernameViewModel.getState();
        changeUsernameState.setUsernameError(errorMessage);
        changeUsernameViewModel.setState(changeUsernameState);
        changeUsernameViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}