package comm.entity;

import java.util.List;
import java.util.NoSuchElementException;

public class UserListIterator implements  UserIterator{
    private List<User> users;
    private int index;
    @Override
    public boolean hasNext() {
        return (index < users.size());
    }
    @Override
    public User next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        User user = users.get(index);
        index++;
        return user;
    }

    public UserListIterator(List<User> users) {
        this.users = users;
        this.index = 0;
    }


}
