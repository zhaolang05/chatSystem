package use_case.changepassword;

public interface ChangePasswordOutPutBoundary {
    void prepareSuccessView(ChangePasswordOutputData user);
    void prepareFailView(String error);
}
