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

    @Test
    public void should_return_ticket_and_store_to_2nd_locker_when_manager_store_bag_given_2_lockers_no_robot_1st_locker_full_and_2nd_locker_has_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(5);
        Locker secondLocker = createLockerWithUsedSlot(2);
        lockers.add(firstLocker);
        lockers.add(secondLocker);
        LockerRobotManager manager = new LockerRobotManager(lockers);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, secondLocker.pickUpBag(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_2_lockers_no_robot_and_all_locker_have_no_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(5);
        Locker secondLocker = createLockerWithUsedSlot(5);
        lockers.add(firstLocker);
        lockers.add(secondLocker);
        LockerRobotManager manager = new LockerRobotManager(lockers);
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class,() -> {
            manager.store(bag);
        });
    }
}
