package cn.xpbootcamp.locker;

public class FullCapacityException extends RuntimeException {
    public FullCapacityException(String errorMessage) {
        super(errorMessage);
    }
}
