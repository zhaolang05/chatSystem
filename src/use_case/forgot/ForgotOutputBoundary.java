package use_case.forgot;

public interface ForgotOutputBoundary {
    void prepareSuccessView(ForgotOutputData user);

    void prepareLoginView(String username);
    
    void prepareFailView(String error);

}