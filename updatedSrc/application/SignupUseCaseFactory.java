package application;



import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signUp.SignUpController;
import interface_adapter.signUp.SignUpPresenter;
import interface_adapter.signUp.SignUpViewModel;
import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInteractor;
import use_case.signUp.SignUpOutputBoundary;
import use_case.signUp.SignUpUserDataAccessInterface;
import view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignUpView create(
            ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, SignUpUserDataAccessInterface
            userDataAccessObject, LoginViewModel loginViewModel) throws IOException {

           try{ SignUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, userDataAccessObject, loginViewModel);
               return new SignUpView(signupController, signupViewModel, viewManagerModel);}
           catch (IOException ex) { JOptionPane.showMessageDialog(null, "fail");}

            return null;

    }


    private static SignUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, SignUpUserDataAccessInterface userDataAccessObject,
    LoginViewModel loginViewModel) throws IOException {

        SignUpOutputBoundary signupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel);

        SignUpInputBoundary userSignupInteractor = new SignUpInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new SignUpController(userSignupInteractor);
    }
}
