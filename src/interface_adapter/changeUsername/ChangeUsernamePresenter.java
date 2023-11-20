package interface_adapter.changeUsername;

import interface_adapter.ViewManagerModel;
import use_case.changeUsername.ChangeUsernameOutputBoundary;
import use_case.changeUsername.ChangeUsernameOutputData;

public class ChangeUsernamePresenter implements ChangeUsernameOutputBoundary {
    private ChangeUsernameViewModel changeUsernameviewModel;
    private ViewManagerModel viewManagerModel;

    @Override
    public void prepareSuccessView(ChangeUsernameOutputData outputData) {
        ChangeUsernameState state = changeUsernameviewModel.getState();
        state.setNewUsername(outputData.getNewUsername());
        this.changeUsernameviewModel.setState(state);
        this.changeUsernameviewModel.firePropertyChanged();

        viewManagerModel.setActiveView(changeUsernameviewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}
