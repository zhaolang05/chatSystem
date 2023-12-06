package use_case.forgot;

public interface ForgotInputBoundary {
    void execute(ForgotInputData signupInputData);
    void back(String username);
}
