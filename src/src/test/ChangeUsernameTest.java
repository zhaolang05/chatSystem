import data_access.FileUserDataAccessObject;
import entity.CommonUser;
import org.junit.Test;
import use_case.changeUsername.*;
import use_case.signUp.SignUpInteractor;


import static org.junit.Assert.*;

public class ChangeUsernameTest {
    @Test
    public void successTest() {
        FileUserDataAccessObject userRepository = new FileUserDataAccessObject();
        int[] userid = SignUpInteractor.userIdGenerator();
        CommonUser user = new CommonUser("Kerry", "123", userid);
        userRepository.save(user);
        ChangeUsernameInputData inputData = new ChangeUsernameInputData("Christine", userid);


        ChangeUsernameOutputBoundary successPresenter = new ChangeUsernameOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeUsernameOutputData username) {
                assertEquals(username.getNewUsername(), "Christine");
            }

            public void prepareFailView(ChangeUsernameOutputData outputData) {
                fail("usecase failure not expected");
            }

            };
        ChangeUsernameInputBoundary interactor = new ChangeUsernameInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
}}
