package pharma.magazine.adapters.api.endpoint.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import pharma.magazine.adapters.api.endpoint.utils.ErrorUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
class ErrorInfo {
    @JsonProperty("error_description")
    private String errorDescription;
    private LocalDateTime time;

    ErrorInfo(HttpServletRequest request, MessageSource messageSource) {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (throwable != null && throwable.getMessage() != null)
            this.errorDescription = ErrorUtils.resolveMessage(throwable.getMessage(), messageSource, request.getLocale());
        this.time = LocalDateTime.now();
    }

}