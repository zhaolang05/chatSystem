package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.forgot.ForgotController;
import interface_adapter.forgot.ForgotPresenter;
import interface_adapter.forgot.ForgotViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.forgot.ForgotInputBoundary;
import use_case.forgot.ForgotInteractor;
import use_case.forgot.ForgotOutputBoundary;
import use_case.forgot.ForgotUserDataAccessInterface;
import view.ForgotView;

public class ForgotUseCaseFactory {

    /** Prevent instantiation. */
    private ForgotUseCaseFactory() {}

    public static ForgotView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, ForgotViewModel forgotViewModel, ForgotUserDataAccessInterface userDataAccessObject) {
        try {
            ForgotController forgotController = createUserForgotUseCase(viewManagerModel, forgotViewModel, loginViewModel, userDataAccessObject);
            return new ForgotView(forgotController, forgotViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ForgotController createUserForgotUseCase(ViewManagerModel viewManagerModel, ForgotViewModel forgotViewModel, LoginViewModel loginViewModel, ForgotUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ForgotOutputBoundary forgotOutputBoundary = new ForgotPresenter(viewManagerModel, loginViewModel, forgotViewModel);

        UserFactory userFactory = new CommonUserFactory();

        ForgotInputBoundary userForgotInteractor = new ForgotInteractor(
                userDataAccessObject, forgotOutputBoundary);

        return new ForgotController(userForgotInteractor);
    }
}
