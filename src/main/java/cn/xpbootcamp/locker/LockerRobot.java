package cn.xpbootcamp.locker;

import java.util.ArrayList;

abstract public class LockerRobot {
    protected ArrayList<Locker> lockers;

    protected LockerRobot(ArrayList<Locker> lockers) {
        this.lockers = lockers;
    }

    abstract protected Ticket store(Package pack);

    protected Package pickUp(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.contains(ticket)) {
                return locker.pickUpPackage(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
