package use_case.signUp;

import entity.CommonUser;

public interface SignUpUserDataAccessInterface {
    public boolean existsByName(String identity);

    public void save(CommonUser commonUser);
}
