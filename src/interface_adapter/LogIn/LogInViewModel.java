package interface_adapter.LogIn;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LogInViewModel extends ViewModel {
    public LogInViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
