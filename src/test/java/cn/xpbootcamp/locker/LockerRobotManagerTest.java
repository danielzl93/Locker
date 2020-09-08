package cn.xpbootcamp.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class LockerRobotManagerTest {
    private ArrayList<Locker> lockers = new ArrayList<>();
    private ArrayList<LockerRobot> robots = new ArrayList<>();

    private Locker createLockerWithUsedSlot(int usedSlot) {
        int defaultCapacity = 5;
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_manager_store_bag_given_2_lockers_no_robot_all_locker_have_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(3);
        Locker secondLocker = createLockerWithUsedSlot(2);
        lockers.add(firstLocker);
        lockers.add(secondLocker);
        LockerRobotManager manager = new LockerRobotManager(lockers);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, firstLocker.pickUpBag(ticket));
    }
}
