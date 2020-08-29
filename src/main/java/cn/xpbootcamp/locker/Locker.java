package cn.xpbootcamp.locker;
import java.util.List;
import java.util.Random;

public class Locker {
    private int capacity;
    private List<String> issuedTickets;

    public Locker(int capacity, List<String> issuedTickets) {
        this.capacity = capacity;
        this.issuedTickets = issuedTickets;
    }

    public String store() {
        if (capacity < 1) {
            throw new RuntimeException("no capacity");
        }
        capacity -= 1;
        String ticket = generateTicket();
        while(issuedTickets.contains(ticket)) {
            ticket = generateTicket();
        }
        return ticket;
    }

    private String generateTicket() {
        Random rand = new Random();
        int upperbound = 99;
        int int_random = rand.nextInt(upperbound);
        return "barcode_" + int_random;
    }

    public int getCapacity() {
        return capacity;
    }

    public String pickUp(String ticket) {
        if (issuedTickets.contains(ticket)) {
            capacity += 1;
            return "success";
        } else {
            return "invalid ticket";
        }
    }
}
