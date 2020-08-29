package cn.xpbootcamp.locker;

public class Locker {
    int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public String store() {
        return "barcode_01";
    }
}
