package ba.ibu.edu.bemytech.core.exceptions.auth;

import ba.ibu.edu.bemytech.common.constants.HttpCodes;
import ba.ibu.edu.bemytech.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NoAccessException extends GeneralException {
    public NoAccessException() {
        super(HttpCodes.FORBIDDEN);
    }

    public NoAccessException(String message) {
        super(HttpCodes.FORBIDDEN, message);
    }
}
