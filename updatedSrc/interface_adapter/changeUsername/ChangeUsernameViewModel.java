package interface_adapter.changeUsername;

import interface_adapter.ViewModel;
import interface_adapter.signUp.SignUpState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChangeUsernameViewModel extends ViewModel {
    ChangeUsernameState state = new ChangeUsernameState();
    public static final String TITLE_LABEL = "Change Username View";
    public static final String NEW_USERNAME_LABEL = "Pick your new username";

    public static final String USER_ID_LABEL = "UserID";

    public static final String CHANGEUSERNAME_BUTTON_LABEL = "Change Username";


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ChangeUsernameViewModel() {super("Change Username View");}

    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public void setState(ChangeUsernameState state){
        this.state = state;
    }

    public ChangeUsernameState getState(){
        return state;
    }
}
