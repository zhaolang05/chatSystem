package interface_adapter.chat;

public class ChatState {
    private String username = "";
    private String error = null;
    private String message = "";


    public ChatState(ChatState copy) {
        username = copy.username;
        error = copy.error;
        message = copy.message;

    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChatState() {}

    public String getUsername() {
        return username;
    }


    public String getMessage() {
        return message;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
