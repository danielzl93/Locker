package cn.xpbootcamp.locker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Locker {
    private int capacity;
    private List<String> issuedTickets;
    private List<String> expiredTickets;

    public Locker(int capacity) {
        this.capacity = capacity;
        this.issuedTickets = new ArrayList<>();
        this.expiredTickets = new ArrayList<>();
    }

    public Locker(int capacity, List<String> issuedTickets) {
        this.capacity = capacity;
        this.issuedTickets = issuedTickets;
        this.expiredTickets = new ArrayList<>();
    }

    public Locker(int capacity, List<String> issuedTickets, List<String> expiredTickets) {
        this.capacity = capacity;
        this.issuedTickets = issuedTickets;
        this.expiredTickets = expiredTickets;
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
        issuedTickets.add(ticket);
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
            if (expiredTickets.contains(ticket)) {
                throw new RuntimeException("invalid ticket");
            }
            capacity += 1;
            expiredTickets.add(ticket);
            return "success";
        } else {
            return "invalid ticket";
        }
    }
}
