package cn.xpbootcamp.locker;
import java.util.*;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Package> ticketPackageMap = new HashMap<>();
    private final ArrayList<Ticket> usedTicket = new ArrayList<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Package pack) {
        if (capacity - ticketPackageMap.size() < 1) {
            throw new FullCapacityException("no capacity");
        }
        Ticket ticket = new Ticket();
        ticketPackageMap.put(ticket, pack);
        return ticket;
    }

    public Package pickUpPackage(Ticket ticket) {
        if (ticketPackageMap.containsKey(ticket)) {
            usedTicket.add(ticket);
            return ticketPackageMap.remove(ticket);
        } else if (usedTicket.contains(ticket)) {
            throw new UsedTicketException("used ticket");
        } else {
            throw new InvalidTicketException("invalid ticket");
        }
    }
}
