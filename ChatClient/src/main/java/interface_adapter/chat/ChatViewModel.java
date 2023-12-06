package interface_adapter.chat;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel extends ViewModel {

    public final String TITLE_LABEL = "My Chat System";


    private ChatState state = new ChatState();

    public ChatViewModel() {
        super("Chat Main");
    }

    public void setState(ChatState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ChatState getState() {
        return state;
    }
}
