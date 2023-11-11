package project.backend.global.validator;

import org.springframework.stereotype.Component;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class LocalDateTimeValidation {

    private static final String DATE_PATTERN = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]";
    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    // 문자열을 LocalDateTime으로 변환
    public static LocalDateTime convertStringToLocalDateTime(String date){
        if(!pattern.matcher(date).matches()){
            throw new BusinessException(ErrorCode.LOCAL_DATE_TIME_VALIDATOR);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime;
    }
}