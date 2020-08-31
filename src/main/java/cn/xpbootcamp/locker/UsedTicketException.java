package cn.xpbootcamp.locker;

public class UsedTicketException extends RuntimeException{
    public UsedTicketException(String errorMessage) {
        super(errorMessage);
    }
}
