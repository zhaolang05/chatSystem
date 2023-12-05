package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    final private String profile;
    final  private  String avatar;

    final  private String personalizedSign;

    public SignupInputData(String username, String password, String repeatPassword,String profile,String personalizedSign,String avatar) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.profile=profile;
        this.personalizedSign=personalizedSign;
        this.avatar=avatar;
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

    public String getPersonalizedSign() {
        return personalizedSign;
    }

    public String getAvatar() {
        return avatar;
    }
}
