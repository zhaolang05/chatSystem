import data_access.FileUserDataAccessObject;
import use_case.signUp.SignUpOutputBoundary;
import use_case.signUp.SignUpOutputData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testSignUpPresenter implements SignUpOutputBoundary {
    private final FileUserDataAccessObject userRepository;

    public testSignUpPresenter(FileUserDataAccessObject userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void prepareSuccessView(SignUpOutputData user) {
        assertEquals("Kerry", user.getUserName());
        assertTrue(userRepository.existsByName(user.getUserName()));
    }

    @Override
    public void prepareFailView(String error) {

    }
}
