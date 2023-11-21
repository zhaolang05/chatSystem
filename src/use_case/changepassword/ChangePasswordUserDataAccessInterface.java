package use_case.changepassword;

import entity.CommonUser;

public interface ChangePasswordUserDataAccessInterface {
    boolean existsByName(String identifier);
    CommonUser getUser(String username);
}
