package use_case.signup;

import comm.entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
