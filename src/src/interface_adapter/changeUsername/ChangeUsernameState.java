package interface_adapter.changeUsername;

public class ChangeUsernameState {
    private String newUsername;

    private int[] userID;

    public ChangeUsernameState(){}

    public ChangeUsernameState( ChangeUsernameState copy){ this.newUsername = copy.newUsername;
    this.userID = copy.userID;}

    public String getNewUsername(){return newUsername;}

    public void setNewUsername(String newUsername) {this.newUsername = newUsername;}

    public int[] getUserID(){return userID;}

    public void setUserID(int[] userID) {this.userID = userID;}
}
