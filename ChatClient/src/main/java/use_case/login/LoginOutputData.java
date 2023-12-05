package use_case.login;

import comm.entity.User;

import java.util.List;
import java.util.Map;

public class LoginOutputData {

    private final User user;
    private final Map<String,List<User>> friends;
    private boolean useCaseFailed;

    public LoginOutputData(User user, Map<String,List<User>> friends,boolean useCaseFailed) {
        this.user = user;
        this.friends=friends;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public Map<String,List<User>> getFriends() {
        return friends;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }
}
