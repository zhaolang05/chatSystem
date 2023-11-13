package use_case.SignUp;

import entity.CommonUser;

public interface SignUpUserDataAccessInterface {
    public boolean existByName(String identity);

    public void save(CommonUser commonUser);
}
