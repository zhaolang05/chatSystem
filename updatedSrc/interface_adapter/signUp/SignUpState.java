package interface_adapter.signUp;

public class SignUpState {
    private String username;
    private String password;
    private String repeatPassword;

    private String repeatPasswordError;

    private String usernameError;

    public SignUpState() {
    }

    public SignUpState(SignUpState copy) {
        username = copy.username;
        password = copy.password;
        repeatPassword = copy.repeatPassword;
    }

    public void setUsername(String username){this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public void setRepeatPassword(String repeatPassword) {this.repeatPassword = repeatPassword;}

    public void setRepeatPasswordError(String error){this.repeatPasswordError = error;}

    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setUsernameError(String usernameError){this.usernameError = usernameError;}

    public String getUsernameError(){return usernameError;}

    public String getRepeatPasswordError(){return repeatPasswordError;}

    public void flush(){this.repeatPasswordError = null; this.usernameError = null;}
}