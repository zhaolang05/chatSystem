package interface_adapter.changepassword;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChangePasswordViewModel extends ViewModel {
    public final String TITLE_LABEL = "Change Password View";
    public final String USERNAME_LABEL = "Enter new password";
    public final String PASSWORD_LABEL = "Repeat new password";

    public static final String FORGOTPASSWORD_BUTTON_LABEL = "Enter new password";
    public static final String REENTER_LABEL = "Repeat your new password";

    public static final String DONE_BUTTON_LABEL = "Done";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private ChangePasswordState state = new ChangePasswordState();

    public ChangePasswordViewModel() {
        super("for got password");
    }

    public void setState(ChangePasswordState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
            support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ChangePasswordState getState() { return state; }
}
