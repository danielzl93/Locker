package cn.xpbootcamp.locker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends LockerRobot{
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }
    @Override
    public Ticket store(Bag bag) {
        Optional<Locker> maxFreeSlotLocker = lockers.stream().max(Comparator.comparing(Locker::getFreeSlot));
        if (maxFreeSlotLocker.isPresent()){
            return maxFreeSlotLocker.get().store(bag);
        }

        throw new FullCapacityException();
    }
}
