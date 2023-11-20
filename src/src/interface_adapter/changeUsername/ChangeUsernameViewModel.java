package interface_adapter.changeUsername;

import interface_adapter.ViewModel;
import interface_adapter.signUp.SignUpState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChangeUsernameViewModel extends ViewModel {
    ChangeUsernameState state = new ChangeUsernameState();
    public static final String TITLE_LABEL = "Change Username View";
    public static final String USERNAME_LABEL = "Enter your new username";

    public static final String CHANGEUSERNAME_BUTTON_LABEL = "Change Username";


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ChangeUsernameViewModel(String viewName) {super(viewName);}

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
