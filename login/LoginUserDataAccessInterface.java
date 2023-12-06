package use_case.login;

import entity.CommonUser;
import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(CommonUser user);

    CommonUser get(String username);
}
