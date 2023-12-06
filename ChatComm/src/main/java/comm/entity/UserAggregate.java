package comm.entity;

public interface UserAggregate {
    UserIterator iterator();
    void add(User user);
}
