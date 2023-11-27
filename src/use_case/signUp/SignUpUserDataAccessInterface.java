package use_case.signUp;

import entity.CommonUser;
import entity.User;

public interface SignUpUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(CommonUser user);
}
