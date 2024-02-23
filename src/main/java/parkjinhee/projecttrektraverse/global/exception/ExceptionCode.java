package parkjinhee.projecttrektraverse.global.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionCode {
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."),
    BOARD_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 게시판입니다."),
    THEME_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."),
    THEME_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 게시판입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");

    private HttpStatus status;
    private String message;

    private ExceptionCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}

