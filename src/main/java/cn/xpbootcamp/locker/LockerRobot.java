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

    public int getFreeSlot() {
        return lockers.stream().mapToInt(Locker::getFreeSlot).sum();
    }

    public int getCapacity() {
        return lockers.stream().mapToInt(Locker::getCapacity).sum();
    }

    @Override
    public String createReport() {
        StringBuilder builder = new StringBuilder(String.format("R %d %d\n", getFreeSlot(), getCapacity()));

        for (Locker locker : lockers) {
            builder.append("\t");
            builder.append(locker.createReport());
        }
        return builder.toString();
    }
}
