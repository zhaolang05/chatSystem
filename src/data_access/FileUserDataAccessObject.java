package data_access;

import entity.CommonUser;
import use_case.LogIn.LogInUserDataAccessInterface;
import use_case.SignUp.SignUpUserDataAccessInterface;


import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignUpUserDataAccessInterface, LogInUserDataAccessInterface{


        private final Map<String, CommonUser> accounts = new HashMap<>();


        @Override
        public void save(CommonUser user) {
            accounts.put(user.getUserName(), user);
        }

        @Override
        public CommonUser get(String username) {
            return accounts.get(username);
        }
        /**
         * Return whether a user exists with username identifier.
         *
         * @param identifier the username to check.
         * @return whether a user exists with username identifier
         */
        @Override
        public boolean existsByName(String identifier) {
            return accounts.containsKey(identifier);
        }




}
