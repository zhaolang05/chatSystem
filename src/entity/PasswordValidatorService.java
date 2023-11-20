package entity;

public class PasswordValidatorService implements PasswordValidator{
    @Override
    public boolean validPassword(String password) {
        return password != null && password.length() > 5;
    }
}
