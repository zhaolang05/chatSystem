package comm.entity;

import java.util.List;
import java.util.Map;

public class Friend implements UserAggregate {
    private String name;
    private Map<String, List<String>> friendsNameGroup;
    private Map<String, List<User>> friendsGroup;



    public Map<String, List<User>> getFriendsGroup() {
        return friendsGroup;
    }

    public void setFriendsGroup(Map<String, List<User>> friendsGroup) {
        this.friendsGroup = friendsGroup;
    }

    private List<User> friends;
    private UserListIterator users;

    public boolean existFriend(String userName) {
        while (users.hasNext()) {
            User user = users.next();
            if (user.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }


    public Friend(String name, Map<String, List<String>> friendName) {
        this.name = name;
        this.friendsNameGroup = friendName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getFriendsNameGroup() {
        return friendsNameGroup;
    }

    public void setFriendsNameGroup(Map<String, List<String>> friendsNameGroup) {
        this.friendsNameGroup = friendsNameGroup;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public UserIterator iterator() {
        return new UserListIterator(friends);
    }

    @Override
    public void add(User user) {
        friends.add(user);
    }



}
