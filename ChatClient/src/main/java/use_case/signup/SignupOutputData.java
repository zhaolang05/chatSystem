package use_case.signup;

import java.time.LocalDateTime;

public class SignupOutputData {

    private final String username;
    private LocalDateTime creationTime;

    private boolean useCaseFailed;

    public SignupOutputData(String username, LocalDateTime creationTime, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

}
