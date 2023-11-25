package use_case.changeUsername;


public interface ChangeUsernameOutputBoundary {
    void prepareSuccessView(ChangeUsernameOutputData outputData);

    void prepareFailView(String errorMessage);


}
