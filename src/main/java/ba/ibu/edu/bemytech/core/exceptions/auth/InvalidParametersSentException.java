package ba.ibu.edu.bemytech.core.exceptions.auth;

import ba.ibu.edu.bemytech.common.constants.HttpCodes;
import ba.ibu.edu.bemytech.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParametersSentException extends GeneralException {
    public InvalidParametersSentException() {
        super(HttpCodes.BAD_REQUEST);
    }

    public InvalidParametersSentException(String message) {
        super(HttpCodes.BAD_REQUEST, message);
    }
}