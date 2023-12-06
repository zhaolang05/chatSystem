package use_case.chat;

import comm.entity.ChatMessage;
import comm.entity.User;
import comm.event.EventListener;
import view.AddFriendFrame;
import view.ChangePasswordFrame;
import view.ChangeUserNameFrame;

public interface ChatInputBoundary {
    void execute(ChatInputData chatInputData);

    void  registerChatWindows(String userName,Chat chat);


    void  registerChangePasswordWindow(ChangePasswordFrame changePasswordFrame);

    void registerAddFriendWindow(AddFriendFrame addFriendFrame);

   void registerChangeUserNameWindow(ChangeUserNameFrame changeUserNameFrame);
    void  sendMessage(String friend, String message, String messageType);


    void registerListener(EventListener listener);

    void disposeChatWindow();

    void  sendMessage(User user, String messageType);
    void  sendMessage(ChatMessage message);
    void toChat();
}
