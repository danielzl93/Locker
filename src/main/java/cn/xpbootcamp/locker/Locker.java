package cn.xpbootcamp.locker;
import java.util.*;

public class Locker implements Storable {
    private final int capacity;
    private final Map<Ticket, Bag> ticketPackageMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Ticket store(Bag bag) {
        if (isFull()) {
            throw new FullCapacityException();
        }
        Ticket ticket = new Ticket();
        ticketPackageMap.put(ticket, bag);
        return ticket;
    }

    @Override
    public Bag pickUpWith(Ticket ticket) {
        if (contains(ticket)) {
            return ticketPackageMap.remove(ticket);
        }
        throw new InvalidTicketException();
    }

    protected boolean contains(Ticket ticket) {
        return ticketPackageMap.containsKey(ticket);
    }

    @Override
    public boolean isFull() {
        return capacity - ticketPackageMap.size() < 1;
    }

    @Override
    public String createReport() {
        return String.format("L %d %d\n", getFreeSlot(), getCapacity());
    }

    @Override
    public int getFreeSlot() { return capacity - ticketPackageMap.size(); }

    @Override
    public int getCapacity() {
        return capacity;
    }

}
