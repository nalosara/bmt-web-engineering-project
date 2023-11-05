package ba.ibu.edu.bemytech.core.exceptions.repository;

import ba.ibu.edu.bemytech.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.bemytech.common.constants.HttpCodes.NOT_FOUND;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GeneralException {
    public ResourceNotFoundException(String message) {
        super(NOT_FOUND, message);
    }

    public ResourceNotFoundException() {
        super(NOT_FOUND);
    }
}