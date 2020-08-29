package cn.xpbootcamp.locker;

public class Locker {
    private int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public String store() {
        if (capacity < 1) {
            return "no capacity";
        }
        capacity -= 1;
        return "barcode_01";
    }

    public int getCapacity() {
        return capacity;
    }
}
