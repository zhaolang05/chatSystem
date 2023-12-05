package use_case.login;

import comm.entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
