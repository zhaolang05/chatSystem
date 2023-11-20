package use_case.ForgotPassword;

public class ForgotPasswordInputData {
    private String username;
    private String password;

    public ForgotPasswordInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassWord() {return password; }

}
