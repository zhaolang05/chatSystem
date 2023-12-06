package test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import org.junit.Test;
import use_case.forgot.*;
import use_case.login.LoginInputData;

import static org.junit.Assert.*;

public class ForgotUseCaseTest {
    @Test
    public void ChangeSuccessTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        ForgotInputData forgotInputData = new ForgotInputData("o", "111", "111");

        ForgotOutputBoundary forgotPresenter = new ForgotOutputBoundary() {
            @Override
            public void prepareSuccessView(ForgotOutputData user) {
                String username = user.getUsername();
                assertEquals(user.getUsername(), "o");
                assertTrue(userDataAccessObject.existsByName(username));
                assertEquals(userDataAccessObject.get(username).getAccountPassword(), "111");
            }

            @Override
            public void prepareLoginView(String username) { fail();}

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };
        ForgotInputBoundary forgotInteractor = new ForgotInteractor(userDataAccessObject, forgotPresenter);
        forgotInteractor.execute(forgotInputData);

    }

    @Test
    public void userExistsFailedTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        ForgotInputData forgotInputData = new ForgotInputData("ol", "111", "111");

        ForgotOutputBoundary forgotPresenter = new ForgotOutputBoundary() {
            @Override
            public void prepareSuccessView(ForgotOutputData user) {
                fail();
            }

            @Override
            public void prepareLoginView(String username) {}

            @Override
            public void prepareFailView(String error) {
                assertEquals("User doesn't exist.", error);
            }
        };
        ForgotInputBoundary forgotInteractor = new ForgotInteractor(userDataAccessObject, forgotPresenter);
        forgotInteractor.execute(forgotInputData);

    }

    @Test
    public void passwordMatchFailedTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        ForgotInputData forgotInputData = new ForgotInputData("o", "120", "110");

        ForgotOutputBoundary forgotPresenter = new ForgotOutputBoundary() {
            @Override
            public void prepareSuccessView(ForgotOutputData user) {
                fail();
            }

            @Override
            public void prepareLoginView(String username) {}

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        ForgotInputBoundary forgotInteractor = new ForgotInteractor(userDataAccessObject, forgotPresenter);
        forgotInteractor.execute(forgotInputData);
    }

    @Test
    public void loginViewPrepareSuccessTest(){
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("o", "123");
        userDataAccessObject.save(user);
        ForgotInputData forgotInputData = new ForgotInputData("o", "120", "110");
        ForgotOutputBoundary forgotPresenter = new ForgotOutputBoundary() {
            @Override
            public void prepareSuccessView(ForgotOutputData user) {
                fail();
            }

            @Override
            public void prepareLoginView(String username) {
                assertTrue(userDataAccessObject.existsByName(username));
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };

        ForgotInputBoundary forgotInteractor = new ForgotInteractor(userDataAccessObject, forgotPresenter);
        forgotInteractor.back(forgotInputData.getUsername());

    }
}
