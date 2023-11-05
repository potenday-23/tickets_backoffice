package project.backend.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // S3
    IMAGE_UPLOAD_FAIL(400, "S001", "이미지를 업로드할 수 없습니다."),

    // Member
    AUTHORIZATION_HEADER_NOT_VALID(400, "M001", "Authorization 헤더가 유효하지 않습니다."),
    MISSING_REDIRECT_REQUEST_PARAM(400, "M002", "Redirect Url 을 설정해야 합니다."),
    MISSING_REQUEST_PARAM(400, "M003", "Request Parameter를 정확하게 설정해야 합니다."),
    KAKAO_CODE_NOT_VALID(400, "M004", "카카오 코드가 유효하지 않습니다."),
    TOKEN_NOT_VALID(400, "M005", "AccessToken이 유효하지 않습니다."),
    USER_NOT_FOUND(400, "U001", "사용자를 찾을 수 없습니다."),

    // Ticket
    TICKET_NOT_FOUND(400, "U001", "사용자를 찾을 수 없습니다.")

    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
