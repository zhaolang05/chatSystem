package application;

import interface_adapter.ViewManagerModel;
import interface_adapter.changeUsername.ChangeUsernameController;
import interface_adapter.changeUsername.ChangeUsernamePresenter;
import interface_adapter.changeUsername.ChangeUsernameViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.changeUsername.ChangeUsernameDataAccessInterface;
import use_case.changeUsername.ChangeUsernameInputBoundary;
import use_case.changeUsername.ChangeUsernameInteractor;
import use_case.changeUsername.ChangeUsernameOutputBoundary;
import view.LoggedInView;

public class LoggedInViewFactory {


    public static LoggedInView create(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                                      ChangeUsernameDataAccessInterface userdataAccessObject,
                                      ChangeUsernameViewModel changeUsernameViewModel) {
        ChangeUsernameController changeUsernameController = createChangeUsernameUseCase(viewManagerModel, changeUsernameViewModel, userdataAccessObject, loggedInViewModel, loginViewModel);
        return new LoggedInView(loggedInViewModel, viewManagerModel, loginViewModel, changeUsernameViewModel, changeUsernameController);

    }

    private static ChangeUsernameController createChangeUsernameUseCase(
            ViewManagerModel viewManagerModel,
            ChangeUsernameViewModel changeUsernameViewModel, ChangeUsernameDataAccessInterface userDataAccessObject, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel
    ) {

        // Notice how we pass this method's parameters to the Presenter.
        ChangeUsernameOutputBoundary changeUsernameBoundary = new ChangeUsernamePresenter(changeUsernameViewModel, viewManagerModel, loggedInViewModel, loginViewModel);


        ChangeUsernameInputBoundary changeUsernameInteractor = new ChangeUsernameInteractor(
                userDataAccessObject, changeUsernameBoundary, loggedInViewModel);

        return new ChangeUsernameController(changeUsernameInteractor);


    }
}