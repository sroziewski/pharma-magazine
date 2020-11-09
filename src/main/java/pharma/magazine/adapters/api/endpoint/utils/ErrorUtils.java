package pharma.magazine.adapters.api.endpoint.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ErrorUtils {

    public static String resolveMessage(String message, MessageSource messageSource, Locale locale) {
        try {
            StringJoiner stringJoiner = new StringJoiner("\n");
            for (String line : Stream.of(message.split("\n")).filter(StringUtils::isNotBlank).collect(Collectors.toList())) {
                String[] elements = line.split("\\|");
                String code = elements[0];
                String[] args = elements.length == 1 ? null : Arrays.copyOfRange(elements, 1, elements.length);
                stringJoiner.add(messageSource.getMessage(code, args, locale));
            }
            return stringJoiner.toString();
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

    public static HttpStatus resolveHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null && statusCode != 500) return HttpStatus.valueOf(statusCode);

        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (throwable != null) {
            return HttpStatus.UNAUTHORIZED;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}