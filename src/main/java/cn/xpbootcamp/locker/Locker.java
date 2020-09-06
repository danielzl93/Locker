package cn.xpbootcamp.locker;
import java.util.*;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Bag> ticketPackageMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    protected Ticket store(Bag bag) {
        if (isFull()) {
            throw new FullCapacityException();
        }
        Ticket ticket = new Ticket();
        ticketPackageMap.put(ticket, bag);
        return ticket;
    }

    protected Bag pickUpBag(Ticket ticket) {
        if (contains(ticket)) {
            return ticketPackageMap.remove(ticket);
        }
        throw new InvalidTicketException();
    }

    protected boolean contains(Ticket ticket) {
        return ticketPackageMap.containsKey(ticket);
    }

    protected boolean isFull() {
        return capacity - ticketPackageMap.size() < 1;
    }

    protected int getFreeSlot() { return capacity - ticketPackageMap.size(); }
}
