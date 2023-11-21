package interface_adapter.changepassword;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.changepassword.ChangePasswordOutPutBoundary;
import use_case.changepassword.ChangePasswordOutputData;

public class ChangePasswordPresenter implements ChangePasswordOutPutBoundary {
    private final ChangePasswordViewModel forgotPasswordViewModel;
    private final LoginViewModel loginViewModel;

    private ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(ChangePasswordViewModel forgotPasswordViewModel, LoginViewModel loginViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.forgotPasswordViewModel = forgotPasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData response) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ChangePasswordState forgotPasswordState = forgotPasswordViewModel.getState();
        forgotPasswordState.setUsernameError(error);
        forgotPasswordViewModel.firePropertyChanged();

    }
}
