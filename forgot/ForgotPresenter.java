package interface_adapter.forgot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.forgot.ForgotOutputBoundary;
import use_case.forgot.ForgotOutputData;

public class ForgotPresenter implements ForgotOutputBoundary {

    private final ForgotViewModel forgotViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public ForgotPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          ForgotViewModel forgotViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.forgotViewModel = forgotViewModel;
    }

    @Override
    public void prepareLoginView(String username) {
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(username);
//        this.loginViewModel.setState(loginState);
//        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(ForgotOutputData response) {
        // On success, switch to the login view.
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ForgotState loginState = forgotViewModel.getState();
        loginState.setUsernameError(error);
        forgotViewModel.firePropertyChanged();
    }
}
