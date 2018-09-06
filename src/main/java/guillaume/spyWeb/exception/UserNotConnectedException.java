package guillaume.spyWeb.exception;

public class UserNotConnectedException extends RuntimeException{
    public UserNotConnectedException() {
        super("The user is not connected, we can't get the session.");
    }
}
