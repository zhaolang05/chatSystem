package data_access;



import comm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject  {

    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */

    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */

    public void save(User user) {
        users.put(user.getName(), user);
    }
}
