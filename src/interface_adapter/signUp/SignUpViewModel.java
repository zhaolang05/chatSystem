package interface_adapter.signUp;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SignUpViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private SignUpState state = new SignUpState();
    public SignUpViewModel() {
        super("Sign up");
    }

    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public void setState(SignUpState state){
        this.state = state;
    }

    public SignUpState getState(){
        return state;
    }
}
