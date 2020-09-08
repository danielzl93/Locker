package cn.xpbootcamp.locker;

import java.util.ArrayList;

abstract public class LockerRobot {
    protected ArrayList<Locker> lockers;

    protected LockerRobot(ArrayList<Locker> lockers) {
        this.lockers = lockers;
    }

    abstract protected Ticket store(Bag bag);

    protected Bag pickUp(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.contains(ticket)) {
                return locker.pickUpBag(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    protected boolean areLockersFull() {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return false;
            }
        }
        return true;
    }
}
