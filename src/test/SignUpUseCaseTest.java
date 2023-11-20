import data_access.FileUserDataAccessObject;
import entity.CommonUser;
import org.junit.Test;

import use_case.signUp.*;

import static org.junit.Assert.*;

public class SignUpUseCaseTest {

    @Test
    public void successTest() {
        SignUpInputData inputData = new SignUpInputData("kerry", "456", "456");
        FileUserDataAccessObject userRepository = new FileUserDataAccessObject();
        //SignUpOutputBoundary successPresenter = new testSignUpPresenter(userRepository);
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                assertEquals("Kerry", user.getUserName());
                assertTrue(userRepository.containsUser(user.getUserName()));

            }
            @Override
            public void prepareFailView(String error) {
                fail("use case failure not expected");
            }

            };


        SignUpInputBoundary interactor = new SignUpInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

    }
    @Test
    public void failurePasswordMismatchTest() {

        SignUpInputData inputData = new SignUpInputData("kerry", "456", "458");
        FileUserDataAccessObject userRepository = new FileUserDataAccessObject();
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                fail("use case success not expected");

            }
            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords do not match", error);
            }

        };
        SignUpInputBoundary interactor = new SignUpInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
   @Test
    public void failureUserExistsTest() {
        FileUserDataAccessObject userRepository = new FileUserDataAccessObject();
        int[] userid = SignUpInteractor.userIdGenerator();
        CommonUser user = new CommonUser("kerry", "777", userid);
        userRepository.save(user);

        SignUpInputData inputData = new SignUpInputData("kerry", "456", "458");
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                fail("use case success not expected");

            }
            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists", error);
            }

        };
        SignUpInputBoundary interactor = new SignUpInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

    }
}
