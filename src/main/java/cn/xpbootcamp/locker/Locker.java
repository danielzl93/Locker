package cn.xpbootcamp.locker;
import java.util.*;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Package> ticketPackageMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Package pack) {
        if (isFull()) {
            throw new FullCapacityException("no capacity");
        }
        Ticket ticket = new Ticket();
        ticketPackageMap.put(ticket, pack);
        return ticket;
    }

    public Package pickUpPackage(Ticket ticket) {
        if (contains(ticket)) {
            return ticketPackageMap.remove(ticket);
        } else {
            throw new InvalidTicketException("invalid ticket");
        }
    }

    public boolean contains(Ticket ticket) {
        return ticketPackageMap.containsKey(ticket);
    }

    public boolean isFull() {
        return capacity - ticketPackageMap.size() < 1;
    }

    public int getFreeSlot() { return capacity - ticketPackageMap.size(); }
}
