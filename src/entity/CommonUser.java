package entity;

class CommonUser implements User{
    private final String userName;
    private String password;


    CommonUser(String username, String password) {
        this.userName = username;
        this.password = password;
    }


    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
       this.password = password;
    }



}
