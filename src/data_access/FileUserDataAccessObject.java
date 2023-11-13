package data_access;

import entity.CommonUser;
import use_case.LogIn.LogInUserDataAccessInterface;
import use_case.SignUp.SignUpUserDataAccessInterface;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignUpUserDataAccessInterface, LogInUserDataAccessInterface{


    private final Map<String, CommonUser> accounts = new HashMap<>();

    public CommonUser get(String username) {
            return accounts.get(username);
        }


//         * @param identifier the username to check.
//         * @return whether a user exists with username identifier
//         */
    @Override
    public boolean existByName(String identity) {
        return accounts.containsKey(identity);
    }

    @Override
    public void save(CommonUser user) {
        accounts.put(user.getUserName(), user);

    }
    public boolean containsUser(String username){return accounts.containsKey(username);}
}
