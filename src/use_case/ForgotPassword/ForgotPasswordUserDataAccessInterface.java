package use_case.ForgotPassword;

import entity.User;

public interface ForgotPasswordUserDataAccessInterface {
    boolean existByName(String identifier);
    void savePassword(User user, String passWord);

    User getUser(String username);

}
