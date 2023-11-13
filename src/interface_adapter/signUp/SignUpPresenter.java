package interface_adapter.signUp;

import interface_adapter.LogIn.LogInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.SignUp.SignUpOutputBoundary;
import use_case.SignUp.SignUpOutputData;

public class SignUpPresenter implements SignUpOutputBoundary {

    private final SignUpViewModel signupViewModel;
    private final LogInViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignUpPresenter(ViewManagerModel viewManagerModel,
                           SignUpViewModel signupViewModel,
                           LogInViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }


    @Override
    public void prepareSuccessView(SignUpOutputData signUpOutputData) {
        LogInState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();



    }

    @Override
    public void prepareFailView(String error) {
        SignUpState signUpState= this.signupViewModel.getState();
        signUpState.setUsernameError(error);
        this.signupViewModel.setState(signUpState);
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();




    }
}
