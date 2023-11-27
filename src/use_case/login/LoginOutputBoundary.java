package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareForgotView(String username);

    void prepareFailView(String error);

    void prepareSignupView();

}