package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobot {
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }
    @Override
    public Ticket store(Bag bag) {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.store(bag);
            }
        }
        throw new FullCapacityException();
    }
}
