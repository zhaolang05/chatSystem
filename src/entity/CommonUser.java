package entity;

public class CommonUser implements User{
    private String userName;
    private String accountPassword;
    private String location;
    private String birthday;

    public void changeName(String name){}
    private void changePassword(String password) {accountPassword = password;}

    public String getUserName(){return userName};

    public String getAccountPassword(){return accountPassword;}


}
