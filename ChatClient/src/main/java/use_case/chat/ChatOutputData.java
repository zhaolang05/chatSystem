package use_case.chat;

public class ChatOutputData {

    private final String username;
    private boolean useCaseFailed;

    public ChatOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
