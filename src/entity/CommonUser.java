package entity;

public class CommonUser implements User{
    private String userName;
    private String accountPassword;
    private String location;
    private String birthday;
    public CommonUser(String userName, String accountPassword){
        this.userName = userName;
        this.accountPassword = accountPassword;
    }
    public void changeName(String name){}
    private void changePassword(String password) {accountPassword = password;}

    public String getUserName(){return userName;}

    public String getAccountPassword(){return accountPassword;}


}
