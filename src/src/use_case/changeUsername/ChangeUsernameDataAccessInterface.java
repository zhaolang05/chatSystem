package use_case.changeUsername;

import entity.CommonUser;

public interface ChangeUsernameDataAccessInterface {

    CommonUser getLoggedinUser(int[] identity);
}
