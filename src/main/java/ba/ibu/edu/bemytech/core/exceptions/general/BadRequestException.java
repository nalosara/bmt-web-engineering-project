package ba.ibu.edu.bemytech.core.exceptions.general;

import ba.ibu.edu.bemytech.common.constants.HttpCodes;
import ba.ibu.edu.bemytech.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends GeneralException {
    public BadRequestException() {
        super(HttpCodes.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(HttpCodes.BAD_REQUEST, message);
    }
}
