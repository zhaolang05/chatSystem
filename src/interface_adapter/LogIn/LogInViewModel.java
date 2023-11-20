package interface_adapter.LogIn;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LogInViewModel extends ViewModel {

    private LogInState state;
    public LogInViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public LogInState getState () {
        return state;
    }

    public void setState(LogInState state){this.state = state;}
}
