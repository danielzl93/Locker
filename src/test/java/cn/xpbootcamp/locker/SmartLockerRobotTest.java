package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SmartLockerRobotTest {

    private ArrayList<Locker> lockers = new ArrayList<>();
    private SmartLockerRobot smartLockerRobot;

    private Locker createLockerWithUsedSlot(int usedSlot) {
        int defaultCapacity = 5;
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    @BeforeEach
    public void init() {
        lockers.clear();
        smartLockerRobot = new SmartLockerRobot(lockers);
    }

    @Test
    public void should_return_ticket_and_store_to_locker_when_robot_store_package_given_1_locker_has_free_slots() {
        Locker locker = createLockerWithUsedSlot(0);
        lockers.add(locker);

        Bag expectBag = new Bag();
        Ticket ticket = smartLockerRobot.store(expectBag);

        Assertions.assertEquals(expectBag, locker.pickUpWith(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_robot_store_package_given_managing_1_locker_have_0_free_slots() {
        Locker locker = createLockerWithUsedSlot(5);
        lockers.add(locker);

        Bag expectBag = new Bag();

        Assertions.assertThrows(FullCapacityException.class, () -> {
            smartLockerRobot.store(expectBag);
        });
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_robot_store_package_given_2_lockers_and_1st_locker_has_max_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(0);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(3);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();
        Ticket ticket = smartLockerRobot.store(expectBag);

        Assertions.assertEquals(expectBag, firstLocker.pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_store_to_2nd_locker_when_robot_store_package_given_2_lockers_and_2nd_locker_has_max_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(3);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(2);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();
        Ticket ticket = smartLockerRobot.store(expectBag);

        Assertions.assertEquals(expectBag, secondLocker.pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_robot_store_package_given_2_lockers_and_both_have_same_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(3);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(3);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();
        Ticket ticket = smartLockerRobot.store(expectBag);

        Assertions.assertEquals(expectBag, firstLocker.pickUpWith(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_robot_store_package_given_2_lockers_and_all_have_0_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(5);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(5);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();

        Assertions.assertThrows(FullCapacityException.class, () -> {
            smartLockerRobot.store(expectBag);
        });
    }

    @Test
    public void should_return_package_when_robot_pick_up_package_given_2_lockers_and_valid_ticket() {
        Locker firstLocker = createLockerWithUsedSlot(0);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(3);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();
        Ticket ticket = secondLocker.store(expectBag);

        Assertions.assertEquals(expectBag, smartLockerRobot.pickUpWith(ticket));
    }


    @Test
    public void should_throw_invalid_ticket_when_robot_pick_up_package_given_2_lockers_and_invalid_ticket() {
        Locker firstLocker = createLockerWithUsedSlot(0);
        lockers.add(firstLocker);
        Locker secondLocker = createLockerWithUsedSlot(3);
        lockers.add(secondLocker);

        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            smartLockerRobot.pickUpWith(ticket);
        });
    }

}
