package entity;


public class CommonUser implements User{
    private String userName;
    private String accountPassword;

    private int[] userID;

    public CommonUser(String userName, String accountPassword, int[] userID){
        this.userName = userName;
        this.accountPassword = accountPassword;
        this.userID = userID;
    }
    private void setAccountPassword(String password) {accountPassword = password;}

    public String getUserName(){return userName;}

    public String getAccountPassword(){return accountPassword;}


    @Override
    public void setUserName(String name) {
        this.userName = name;

    }

    public int[] getUserID(){
        return userID;
    }


}
