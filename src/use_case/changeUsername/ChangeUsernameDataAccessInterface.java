package use_case.changeUsername;

import entity.CommonUser;

public interface ChangeUsernameDataAccessInterface {

    CommonUser get(String identity);
    void save();
}
