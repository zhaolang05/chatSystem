package use_case.signUp;

public class SignUpInputData {
    private final String userName;
    private final String accountPassword;

    private final String repeatAccountPassword;


    public SignUpInputData(String userName, String accountPassword, String repeatAccountPassword) {
        this.userName = userName;
        this.accountPassword = accountPassword;
        this.repeatAccountPassword = repeatAccountPassword;
    }

    public String getUserName(){
        return userName;
    }
    public String getAccountPassword(){
        return accountPassword;
    }
    public String getRepeatAccountPassword(){
        return repeatAccountPassword;
    }
}