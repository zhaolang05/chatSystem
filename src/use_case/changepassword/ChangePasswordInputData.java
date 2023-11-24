package use_case.changepassword;

public class ChangePasswordInputData {
    private String username;
    private String password;

    public ChangePasswordInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
