package use_case.SignUp;

public interface SignUpOutputBoundary {
   public void prepareSuccessView(SignUpOutputData signUpOutputData);
   public void prepareFailView(String error);

}
