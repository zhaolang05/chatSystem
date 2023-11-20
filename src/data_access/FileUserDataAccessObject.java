package data_access;

=======
import entity.CommonUser;
import use_case.LogIn.LogInUserDataAccessInterface;
import use_case.signUp.SignUpUserDataAccessInterface;
import use_case.changeUsername.ChangeUsernameDataAccessInterface;


import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignUpUserDataAccessInterface, LogInUserDataAccessInterface, ForgotPasswordUserDataAccessinterface, ChangeUsernameDataAccessInterface{

    private final Map<int[], CommonUser> userIDs = new HashMap<>();
    private final Map<String, CommonUser> accounts = new HashMap<>();

    public CommonUser retrieve(String userID){return userIDs.get(userID);}

    public CommonUser get(String username) {
            return accounts.get(username);
        }


//         * @param identifier the username to check.
//         * @return whether a user exists with username identifier
//         */
    @Override
    public boolean existByName(String identity) {
        return accounts.containsKey(identity);
    }

    @Override
    public void save(CommonUser user) {
        accounts.put(user.getUserName(), user);
        userIDs.put(user.getUserID(), user);

   public User getUser(String username) {
        return accounts.get(username);
    }
  
   public User getUser(String username) {
        return accounts.get(username);
    }
  
    public boolean existID(int[] identity) {
        return userIDs.containsKey(identity);
    }

    public boolean containsUser(String username){return accounts.containsKey(username);}

    @Override
    public CommonUser getLoggedinUser(int[] identity) {
        return userIDs.get(identity);
    }
  
  
