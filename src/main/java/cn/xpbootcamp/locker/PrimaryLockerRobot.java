package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class PrimaryLockerRobot {
    private ArrayList<Locker> lockers;
    public PrimaryLockerRobot(ArrayList<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Package pack) {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.store(pack);
            }
        }
        throw new FullCapacityException("all lockers are full.");
    }

    public Package pickUp(Ticket ticket) {
        for (Locker locker : lockers) {
           if (locker.contains(ticket)) {
               return locker.pickUpPackage(ticket);
           }
        }
        throw new InvalidTicketException("invalid ticket");
    }
}
