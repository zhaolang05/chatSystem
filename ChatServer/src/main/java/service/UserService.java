package service;

public class UserService {

    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }

}
