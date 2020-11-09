package pharma.magazine.adapters.api.endpoint.error;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pharma.magazine.adapters.api.endpoint.utils.ErrorUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController extends AbstractErrorController {
    private final MessageSource messageSource;

    public ErrorController(ErrorAttributes errorAttributes, MessageSource messageSource) {
        super(errorAttributes);
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorInfo> handleError(HttpServletRequest request) {
        HttpStatus httpStatus = ErrorUtils.resolveHttpStatus(request);
        ErrorInfo errorInfo = new ErrorInfo(request, messageSource);
        return new ResponseEntity<>(errorInfo, httpStatus);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}