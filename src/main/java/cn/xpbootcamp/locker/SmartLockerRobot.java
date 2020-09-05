package cn.xpbootcamp.locker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class SmartLockerRobot extends PrimaryLockerRobot{
    private ArrayList<Locker> lockers;
    public SmartLockerRobot(ArrayList<Locker> lockers) {
        super(lockers);
        this.lockers = lockers;
    }

    @Override
    public Ticket store(Package pack) {
        Optional<Locker> maxFreeSlotLocker = lockers.stream().max(Comparator.comparing(Locker::getFreeSlot));
        if (maxFreeSlotLocker.isPresent()){
            return maxFreeSlotLocker.get().store(pack);
        }

        throw new FullCapacityException("all lockers are full.");
    }
}
