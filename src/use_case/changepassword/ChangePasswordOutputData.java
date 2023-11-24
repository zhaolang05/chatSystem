package use_case.changepassword;

public class ChangePasswordOutputData {
    private final String username;
    private boolean useCaseFailed;

    public ChangePasswordOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
