package use_case.forgot;

import entity.User;

public interface ForgotUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
