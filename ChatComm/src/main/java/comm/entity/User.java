package comm.entity;

import java.time.LocalDateTime;

public class User {

    private String name;
    private String password;
    private LocalDateTime creationTime;

    private String profile;

    public User()
    {}

    public User(String name, String password, String profile,LocalDateTime creationTime)
    {
        this.name=name;
        this.password=password;
        this.profile=profile;
        this.creationTime=creationTime;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
