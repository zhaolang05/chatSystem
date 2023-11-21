package application;

import interface_adapter.ViewManagerModel;
import interface_adapter.changeUsername.ChangeUsernameController;
import interface_adapter.changeUsername.ChangeUsernamePresenter;
import interface_adapter.changeUsername.ChangeUsernameViewModel;
import use_case.changeUsername.ChangeUsernameDataAccessInterface;
import use_case.changeUsername.ChangeUsernameInputBoundary;
import use_case.changeUsername.ChangeUsernameInteractor;
import use_case.changeUsername.ChangeUsernameOutputBoundary;
import view.ChangeUsernameView;

import javax.swing.*;
import java.io.IOException;

public class ChangeUsernameUseCaseFactory {

    public ChangeUsernameUseCaseFactory(){}
    public static ChangeUsernameView create(
            ViewManagerModel viewManagerModel,
            ChangeUsernameViewModel changeUsernameViewModel,
            ChangeUsernameDataAccessInterface userDataAccessObject) {

            ChangeUsernameController changeUsernameController = createChangeUsernameUseCase(viewManagerModel, changeUsernameViewModel, userDataAccessObject);
            return new ChangeUsernameView(changeUsernameViewModel, changeUsernameController);

    }

    private static ChangeUsernameController createChangeUsernameUseCase(
            ViewManagerModel viewManagerModel,
            ChangeUsernameViewModel changeUsernameViewModel, ChangeUsernameDataAccessInterface userDataAccessObject
           ) {

        // Notice how we pass this method's parameters to the Presenter.
        ChangeUsernameOutputBoundary changeUsernameBoundary = new ChangeUsernamePresenter(changeUsernameViewModel, viewManagerModel);


        ChangeUsernameInputBoundary changeUsernameInteractor = new ChangeUsernameInteractor(
                userDataAccessObject, changeUsernameBoundary);

        return new ChangeUsernameController(changeUsernameInteractor);
    }
}

