package comm.entity;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String name;
    private String password;
    private LocalDateTime creationTime;
    private String personalizedSign;

    private String profile;

    private Integer online;
    private String avatar;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonalizedSign() {
        return personalizedSign;
    }

    public void setPersonalizedSign(String personalizedSign) {
        this.personalizedSign = personalizedSign;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public User() {
    }


    public User(String name, String password, String profile, LocalDateTime creationTime,String personalizedSign,String avatar) {

        this.name = name;
        this.password = password;
        this.profile = profile;
        this.creationTime = creationTime;
        this.personalizedSign=personalizedSign;
        this.avatar=avatar;
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
