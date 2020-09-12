package cn.xpbootcamp.locker;


import java.util.List;

abstract public class LockerRobot implements Storable {
    protected List<Locker> lockers;

    protected LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }
    @Override
    public abstract Ticket store(Bag bag);
    @Override
    public Bag pickUpWith(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.contains(ticket)) {
                return locker.pickUpWith(ticket);
            }
        }
        throw new InvalidTicketException();
    }
    @Override
    public boolean isFull() {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return false;
            }
        }
        return true;
    }
}
