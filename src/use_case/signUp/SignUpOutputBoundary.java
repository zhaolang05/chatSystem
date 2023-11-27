package use_case.signUp;

public interface SignUpOutputBoundary {
    void prepareSuccessView(SignUpOutputData user);

    void prepareFailView(String error);
}