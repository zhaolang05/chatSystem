package entity;

public class CommonUser implements User{
    private String userName;
    private String accountPassword;

    public CommonUser(String userName, String accountPassword){
        this.userName = userName;
        this.accountPassword = accountPassword;

    }
    private void setAccountPassword(String password) {accountPassword = password;}

    public String getUserName(){return userName;}

    public String getAccountPassword(){return accountPassword;}


    @Override
    public void setUserName(String name) {
        this.userName = name;

    }



}
