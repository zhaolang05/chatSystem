package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUser;
import org.junit.Test;
import use_case.changepassword.*;

import static org.junit.Assert.*;

public class ChangePasswordTest{
    @Test
    public void Test(){
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject();
        int[] userID = {0,0,0,0,0,0,0,0,0};
        CommonUser user = new CommonUser("olivia", "123", userID);
        userDataAccessObject.save(user);
        ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData("olivia",
                "223");


        ChangePasswordInputBoundary interactor = getChangePasswordInputBoundary(changePasswordInputData, userDataAccessObject);
        interactor.execute(changePasswordInputData);
    }

    private static ChangePasswordInputBoundary getChangePasswordInputBoundary(ChangePasswordInputData changePasswordInputData, FileUserDataAccessObject userDataAccessObject) {
        ChangePasswordOutPutBoundary presenter = new ChangePasswordOutPutBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                assertEquals(changePasswordInputData.getPassword(), "223");
            }

            @Override
            public void prepareFailView(String error) {
                fail("does not equal");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userDataAccessObject, presenter);
        return interactor;
    }

}
