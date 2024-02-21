package parkjinhee.projecttrektraverse.global.exception;

public class ServiceLogicException  extends RuntimeException{
    private final ExceptionCode boardExceptionCode;

    public ServiceLogicException(ExceptionCode boardExceptionCode) {
        super(boardExceptionCode.getMessage());
        this.boardExceptionCode = boardExceptionCode;
    }

    public ExceptionCode getBoardExceptionCode() {
        return this.boardExceptionCode;
    }
}
