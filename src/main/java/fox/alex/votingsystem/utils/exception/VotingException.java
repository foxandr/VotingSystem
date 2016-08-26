package fox.alex.votingsystem.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fox on 26.08.16.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Your time expired")
public class VotingException extends RuntimeException {
    public VotingException(String message) {
        super(message);
    }
}
