package cn.xpbootcamp.locker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class SmartLockerRobot extends LockerRobot{
    public SmartLockerRobot(ArrayList<Locker> lockers) {
        super(lockers);
    }

    protected Ticket store(Package pack) {
        Optional<Locker> maxFreeSlotLocker = lockers.stream().max(Comparator.comparing(Locker::getFreeSlot));
        if (maxFreeSlotLocker.isPresent()){
            return maxFreeSlotLocker.get().store(pack);
        }

        throw new FullCapacityException();
    }
}
