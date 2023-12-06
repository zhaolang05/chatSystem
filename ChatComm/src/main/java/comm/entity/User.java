package comm.entity;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String creationTime;
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


    public User(String name, String password, String profile, String creationTime, String personalizedSign, String avatar) {

        this.name = name;
        this.password = password;
        this.profile = profile;
        this.creationTime = creationTime;
        this.personalizedSign = personalizedSign;
        this.avatar = avatar;
    }

    public User getCopy() {
        User user = new User();
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        user.setOnline(user.getOnline());
        user.setAvatar(user.getAvatar());
        user.setProfile(user.getProfile());
        user.setPersonalizedSign(user.getPersonalizedSign());
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
