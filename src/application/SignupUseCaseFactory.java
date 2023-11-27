package application;

import interface_adapter.login.LoginViewModel;
import interface_adapter.signUp.SignUpController;
import interface_adapter.signUp.SignUpPresenter;
import interface_adapter.signUp.SignUpViewModel;
import use_case.signUp.SignUpUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInteractor;
import use_case.signUp.SignUpOutputBoundary;
import view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignUpView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signUpViewModel, SignUpUserDataAccessInterface userDataAccessObject) {

        try {
            SignUpController signUpController = createUserSignupUseCase(viewManagerModel, signUpViewModel, loginViewModel, userDataAccessObject);
            return new SignUpView(signUpController, signUpViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, LoginViewModel loginViewModel, SignUpUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignUpOutputBoundary signupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignUpInputBoundary userSignupInteractor = new SignUpInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new SignUpController(userSignupInteractor);
    }
}
