package fox.alex.votingsystem.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fox on 29.10.16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong password") //400
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
