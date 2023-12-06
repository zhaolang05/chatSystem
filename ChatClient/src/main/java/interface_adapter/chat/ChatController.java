package interface_adapter.chat;

import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.EventListener;
import lombok.extern.slf4j.Slf4j;
import use_case.chat.Chat;
import use_case.chat.ChatInputBoundary;
import use_case.chat.ChatInputData;
import view.AddFriendFrame;
import view.ChangePasswordFrame;
import view.ChangeUserNameFrame;

@Slf4j
public class ChatController {

    final ChatInputBoundary chatUseCaseInteractor;
    public ChatController(ChatInputBoundary chatUseCaseInteractor) {
        this.chatUseCaseInteractor = chatUseCaseInteractor;
    }


    public void execute(ChatInputData chatInputData) {
        try
        {
            chatUseCaseInteractor.execute(chatInputData);
        }
        catch (Exception ex)
        {
            log.error("execute error",ex);
        }

    }
    public void registerChatWindows(String userName,Chat chat)
    {
        chatUseCaseInteractor.registerChatWindows(userName,chat);
    }


    public void registerListener(EventListener listener)
    {
        chatUseCaseInteractor.registerListener(listener);
    }

    public void sendMessage(String friend,String message,String messageType)
    {
        chatUseCaseInteractor.sendMessage(friend,message, messageType);
    }
    public void sendMessage(User user, String messageType)
    {
        chatUseCaseInteractor.sendMessage(user, messageType);
    }
    public void sendMessage(ChatMessage message)
    {
        chatUseCaseInteractor.sendMessage(message);
    }
    public void disposeChatWindow()
    {
        chatUseCaseInteractor.disposeChatWindow();
    }

    public void toChat()  {
        chatUseCaseInteractor.toChat();
    }
}
