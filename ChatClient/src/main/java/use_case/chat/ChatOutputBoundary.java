package use_case.chat;

import use_case.login.LoginOutputData;

public interface ChatOutputBoundary {
    void prepareSuccessView(ChatOutputData user);

    void prepareFailView(String error);

    void toChat();
}