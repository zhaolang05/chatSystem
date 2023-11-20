package use_case.changeUsername;

public class ChangeUsernameOutputData {
    private String newUserName;

    public ChangeUsernameOutputData(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewUsername(){return newUserName; }
}
