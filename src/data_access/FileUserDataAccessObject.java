package data_access;

import entity.CommonUser;
import use_case.login.LoginUserDataAccessInterface;
import use_case.changepassword.ChangePasswordUserDataAccessInterface;


import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements LoginUserDataAccessInterface, ChangePasswordUserDataAccessInterface {

    private final Map<int[], CommonUser> userIDs = new HashMap<>();
    private final Map<String, CommonUser> accounts = new HashMap<>();

    private final Map<CommonUser, String> accountsAndPassword = new HashMap<>();

    public CommonUser retrieve(String userID) {
        return userIDs.get(userID);
    }

    public CommonUser get(String username) {
        return accounts.get(username);
    }


    @Override
    public boolean existsByName(String identity) {
        return accounts.containsKey(identity);
    }


    @Override
    public void save(CommonUser user) {
        accounts.put(user.getUserName(), user);
        userIDs.put(user.getUserID(), user);
    }

    @Override
    public CommonUser getUser(String username) {
        return accounts.get(username);
    }

}

