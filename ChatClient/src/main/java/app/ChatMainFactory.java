package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ChatController;

import interface_adapter.chat.ChatPresenter;
import interface_adapter.chat.ChatViewModel;
import interface_adapter.login.LoginViewModel;

import use_case.chat.ChatInputBoundary;
import use_case.chat.ChatInteractor;
import view.ChatMainView;
import use_case.chat.ChatOutputBoundary;



public class ChatMainFactory {
    private ChatMainFactory() {
    }

    public static ChatMainView create(
            ViewManagerModel viewManagerModel,LoginViewModel loginViewModel,
            ChatViewModel chatViewModel) {
        ChatController chatController = createChatUseCase(viewManagerModel, loginViewModel, chatViewModel);
        return new ChatMainView(chatViewModel, chatController);

    }
    private static ChatController createChatUseCase(ViewManagerModel viewManagerModel,
                                                      LoginViewModel loginViewModel,  ChatViewModel chatViewModel) {
        ChatOutputBoundary chatOutputBoundary = new ChatPresenter(viewManagerModel, loginViewModel,chatViewModel);
        ChatInputBoundary chatInteractor = new ChatInteractor(chatOutputBoundary);
        return new ChatController(chatInteractor);
    }

}
