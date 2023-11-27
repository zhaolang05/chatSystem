//package data_access;
//
//import entity.User;
//import use_case.signUp.SignUpUserDataAccessInterface;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class InMemoryUserDataAccessObject implements SignUpUserDataAccessInterface {
//
//    private final Map<String, User> users = new HashMap<>();
//
//    /**
//     * @param identifier the user's username
//     * @return whether the user exists
//     */
//    @Override
//    public boolean existsByName(String identifier) {
//        return users.containsKey(identifier);
//    }
//
//
//
//    /**
//     * @param user the data to save
//     */
//    @Override
//    public void save(CommonUser user) {
//        users.put(user.getUserName(), user);
//    }
//}
