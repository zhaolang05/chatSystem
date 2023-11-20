package use_case.signUp;

import entity.CommonUser;

public interface SignUpUserDataAccessInterface {
    public boolean existByName(String identity);

    public void save(CommonUser commonUser);

    public boolean existID(int[] identity);
}
