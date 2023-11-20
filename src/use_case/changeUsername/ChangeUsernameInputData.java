package use_case.changeUsername;

public class ChangeUsernameInputData {
    private String newUsername;

    private int[] userID;

    public ChangeUsernameInputData(String newUsername, int[] userID) {
        this.newUsername = newUsername;
        this.userID = userID;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public int[] getUserID() {
        return userID;
    }
}