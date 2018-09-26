package guillaume.spyWeb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserNotConnectedException extends RuntimeException{
    public UserNotConnectedException() {
        super("The user is not connected, we can't get the session.");
    }
}
