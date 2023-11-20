package use_case.ForgotPassword;

public class ForgotPasswordOutputData {
    private final String username;

    private boolean useCaseFailed;

    public ForgotPasswordOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() { return username; }
}
