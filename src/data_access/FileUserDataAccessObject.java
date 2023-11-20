package data_access;

import entity.User;
import use_case.ForgotPassword.ForgotPasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements LoginUserDataAccessInterface, ForgotPasswordUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, Integer> headers = new LinkedHashMap<>();


    public void FileUserDataAccessObject(){}

    @Override
    public boolean existsByName(String identifier) {
       return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();

    }

    private void save() {

    }

    @Override
    public User getUser(String username) {
        return accounts.get(username);
    }


    @Override
    public boolean existByName(String identifier) {
        return false;
    }

    @Override
    public void savePassword(User user, String password) {

    }
}
