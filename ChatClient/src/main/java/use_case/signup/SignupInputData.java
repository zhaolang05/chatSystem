package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    final private String profile;

    public SignupInputData(String username, String password, String repeatPassword,String profile) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.profile=profile;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getProfile() {
        return profile;
    }
}
