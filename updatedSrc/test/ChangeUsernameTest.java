import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import org.junit.Test;
import use_case.changeUsername.*;
import interface_adapter.logged_in.LoggedInViewModel;



import static org.junit.Assert.*;

public class ChangeUsernameTest {
    @Test
    public void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        CommonUser user = new CommonUser("kerry", "123");
        userRepository.save(user);
        ChangeUsernameInputData inputData = new ChangeUsernameInputData("Christine");


        ChangeUsernameOutputBoundary successPresenter = new ChangeUsernameOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeUsernameOutputData username) {
                assertEquals(username.getNewUsername(), "Christine");
            }

            public void prepareFailView(ChangeUsernameOutputData outputData) {
                fail("usecase failure not expected");
            }

            };
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        loggedInViewModel.getState().setUsername("kerry");
        ChangeUsernameInputBoundary interactor = new ChangeUsernameInteractor(userRepository, successPresenter, loggedInViewModel);
        interactor.execute(inputData);
}}
