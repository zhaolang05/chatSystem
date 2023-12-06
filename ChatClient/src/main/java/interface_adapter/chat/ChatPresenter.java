package interface_adapter.chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.chat.ChatOutputBoundary;
import use_case.chat.ChatOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class ChatPresenter implements ChatOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    private ChatViewModel chatViewModel;

    public ChatPresenter(ViewManagerModel viewManagerModel,
                         LoginViewModel loginViewModel, ChatViewModel chatViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.chatViewModel=chatViewModel;
    }

    @Override
    public void toChat() {
//        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareSuccessView(ChatOutputData response) {
        // On success, switch to the chat main view.
        this.viewManagerModel.setActiveView(chatViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setError(error);
        loginViewModel.firePropertyChanged();
    }
}
