package data_access;

import entity.CommonUser;
import use_case.login.LoginUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signUp.SignUpUserDataAccessInterface;
import use_case.changeUsername.ChangeUsernameDataAccessInterface;


import java.util.HashMap;
import java.util.Map;

    public class InMemoryUserDataAccessObject implements SignUpUserDataAccessInterface, LoginUserDataAccessInterface, ChangeUsernameDataAccessInterface{

        private final Map<String, CommonUser> accounts = new HashMap<>();

        public CommonUser getUser(String username) {
            return accounts.get(username);
        }

        public CommonUser get(String username) {
            return accounts.get(username);
        }

        @Override
        public void save() {

        }


        //         * @param identifier the username to check.
//         * @return whether a user exists with username identifier
//         */
        @Override
        public boolean existsByName(String identity) {
            return accounts.containsKey(identity);
        }

        @Override
        public void save(CommonUser user) {
            accounts.put(user.getUserName(), user);

        }

    }
