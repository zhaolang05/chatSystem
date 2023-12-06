package use_case.forgot;

import entity.CommonUser;
import entity.User;

public interface ForgotUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(CommonUser user);

    CommonUser get(String username);
}
