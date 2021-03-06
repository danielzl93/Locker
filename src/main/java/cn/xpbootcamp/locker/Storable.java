package cn.xpbootcamp.locker;

public interface Storable {
    Ticket store(Bag bag);

    Bag pickUpWith(Ticket ticket);

    boolean isFull();

    String createReport();

    int getFreeSlot();

    int getCapacity();
}
