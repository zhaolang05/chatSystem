package interface_adapter.changeUsername;

public class ChangeUsernameState {
    private String newUsername;

    private String usernameError;


    public ChangeUsernameState(){}

    public ChangeUsernameState( ChangeUsernameState copy){ this.newUsername = copy.newUsername;}

    public String getNewUsername(){return newUsername;}

    public void setNewUsername(String newUsername) {this.newUsername = newUsername;}



    public void setUsernameError(String usernameError){this.usernameError = usernameError;}

    public String getUsernameError(){return this.usernameError;}

    public void flush(){this.usernameError = null;}
}
