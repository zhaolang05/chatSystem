package use_case.signUp;

public interface SignUpOutputBoundary {
   public void prepareSuccessView(SignUpOutputData signUpOutputData);
   public void prepareFailView(String error);

}
