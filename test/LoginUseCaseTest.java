package test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.login.*;
import view.LoginView;

import static org.junit.Assert.*;

public class LoginUseCaseTest {

    @Test
    public void loginSuccessTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        LoginInputData loginInputData = new LoginInputData("o", "123");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                String username = user.getUsername();
                assertEquals(user.getUsername(), "o");
                assertTrue(userDataAccessObject.existsByName(username));
                assertEquals(userDataAccessObject.get(username).getAccountPassword(), "123");
            }

            @Override
            public void prepareForgotView(String username) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSignupView() { fail();}

        };

        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginInteractor.execute(loginInputData);

      }

    @Test
    public void userExistsFailedTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        LoginInputData loginInputData = new LoginInputData("ol", "123");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareForgotView(String username) { fail();}

            @Override
            public void prepareFailView(String error) {
                assertEquals(loginInputData.getUsername() + ": Account does not exist.", error);
            }

            @Override
            public void prepareSignupView() { fail(); }
        };
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginInteractor.execute(loginInputData);

    }

    @Test
    public void passwordMatchFailedTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        LoginInputData loginInputData = new LoginInputData("o", "120");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("passwordMatchTest succeed not expected");
            }

            @Override
            public void prepareForgotView(String username) { fail();}

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for "  + user.getUserName() + ".", error);
            }

            @Override
            public void prepareSignupView() { fail();}
        };
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginInteractor.execute(loginInputData);
    }

    @Test
    public void backSuccessTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        LoginInputData loginInputData = new LoginInputData("o", "123");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareForgotView(String username) {
                assertTrue(userDataAccessObject.existsByName(username));
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSignupView() {
                fail();
            }
        };
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginInteractor.forgot(loginInputData.getUsername());

    }

    @Test
    public void cancelSuccessTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
//        userDataAccessObject.save(user);
//        LoginInputData loginInputData = new LoginInputData("o", "123");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareForgotView(String username) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSignupView() {
                assertEquals(false, userDataAccessObject.existsByName(user.getUserName()));
            }
        };
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
        loginInteractor.back();
    }
}

