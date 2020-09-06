package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class PrimaryLockerRobot extends LockerRobot {
    public PrimaryLockerRobot(ArrayList<Locker> lockers) {
        super(lockers);
    }

    protected Ticket store(Package pack) {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.store(pack);
            }
        }
        throw new FullCapacityException();
    }
}
