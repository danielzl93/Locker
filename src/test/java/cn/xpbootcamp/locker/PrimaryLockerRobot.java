package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class PrimaryLockerRobot {
    private ArrayList<Locker> lockers;
    public PrimaryLockerRobot(ArrayList<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Package pack) {
        for (Locker locker : lockers) {
            try {
                return locker.store(pack);
            } catch (FullCapacityException ignored) {
            }
        }
        throw new FullCapacityException("all lockers are full.");
    }
}
