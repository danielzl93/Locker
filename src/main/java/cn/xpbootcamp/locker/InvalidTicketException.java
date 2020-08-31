package cn.xpbootcamp.locker;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String errorMessage) {
        super(errorMessage);
    }
}
