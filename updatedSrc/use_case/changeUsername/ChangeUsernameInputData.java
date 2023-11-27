package use_case.changeUsername;

public class ChangeUsernameInputData {
    private String newUsername;

    public ChangeUsernameInputData(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

}