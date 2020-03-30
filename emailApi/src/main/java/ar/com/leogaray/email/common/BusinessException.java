package ar.com.leogaray.email.common;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* * Excepcion de negocio base con codigo http 409,
*/

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BusinessException extends Exception {
    private static final long serialVersionUID = 3722118869346319456L;
    private static final String defaultMessagge = "Not Found";

    public BusinessException() {
        super(defaultMessagge);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super("Error Code: " + errorCode + " Message: " + message);
    }
}