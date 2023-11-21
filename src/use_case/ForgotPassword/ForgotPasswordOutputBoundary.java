package use_case.ForgotPassword;

public interface ForgotPasswordOutputBoundary {
    void prepareSuccessView(ForgotPasswordOutputData user);
    void prepareFailView(String error);

}
