package interface_adapter.forgot;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ForgotViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Change password view";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter new password";
    public static final String REPEAT_PASSWORD_LABEL = "Confirm new password";

    public static final String CHANGE_BUTTON_LABEL = "Change";
    public static final String BACK_BUTTON_LABEL = "Cancel";

    private ForgotState state = new ForgotState();

    public ForgotViewModel() {
        super("change password view");
    }

    public void setState(ForgotState state) {
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

    public ForgotState getState() {
        return state;
    }
}
