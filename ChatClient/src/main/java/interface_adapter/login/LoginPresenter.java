package interface_adapter.login;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import use_case.chat.ChatInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.ChatMainView;

import javax.swing.*;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private ChatMainView chatMainView;
    private final  JFrame application;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel, SignupViewModel signupViewModel, ChatMainView chatMainView, JFrame application) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.chatMainView=chatMainView;
        this.application=application;
    }

    @Override
    public void toSignup() {
        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        application.setVisible(false);

        ChatInputData chatInputData=new ChatInputData();
        chatInputData.setApplication(application);
        chatInputData.setUser(response.getUser());
        chatInputData.setFriendList(response.getFriends());
        chatMainView.init(chatInputData);
        chatMainView.setVisible(true);
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setError(error);
        loginViewModel.firePropertyChanged();
    }
}
