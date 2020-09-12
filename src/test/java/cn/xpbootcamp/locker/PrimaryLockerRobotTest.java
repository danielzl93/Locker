package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PrimaryLockerRobotTest {
    private int DEFAULT_CAPACITY = 2;
    private Locker firstLocker;
    private ArrayList<Locker> lockers = new ArrayList<>();
    private PrimaryLockerRobot primaryLockerRobot;

    private void setFullLocker(Locker locker) {
        Bag bag = new Bag();
        Ticket ticket_placeholder1 = locker.store(bag);
        Ticket ticket_placeholder2 = locker.store(bag);
    }

    @BeforeEach
    public void init() {
        firstLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(firstLocker);
        primaryLockerRobot = new PrimaryLockerRobot(lockers);
    }

    @Test
    public void should_return_ticket_when_robot_save_package_given_robot_manage_single_locker_and_has_free_capacity() {
        Bag expectBag = new Bag();
        Ticket ticket = primaryLockerRobot.store(expectBag);

        Assertions.assertTrue(firstLocker.contains(ticket));
        Assertions.assertEquals(expectBag, firstLocker.pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_first_locker_when_save_package_given_robot_manage_multiple_lockers_all_has_space() {
        Bag expectBag = new Bag();
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);
        Ticket ticket = primaryLockerRobot.store(expectBag);

        Assertions.assertEquals(expectBag, firstLocker.pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_first_free_locker_when_robot_save_package_given_robot_manage_multiple_lockers_and_first_locker_full() {
        setFullLocker(firstLocker);
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);

        Bag expectBag = new Bag();
        Ticket ticket = primaryLockerRobot.store(expectBag);

        Assertions.assertFalse(firstLocker.contains(ticket));
        Assertions.assertEquals(expectBag, secondLocker.pickUpWith(ticket));
    }

    @Test
    public void should_throw_full_capacity_robot_save_package_given_robot_manage_multiple_lockers_and_all_lockers_full() {
        Locker fullLocker = new Locker(DEFAULT_CAPACITY);
        setFullLocker(firstLocker);
        setFullLocker(fullLocker);
        lockers.add(fullLocker);

        Bag bag = new Bag();
        Assertions.assertThrows(FullCapacityException.class, () -> {
            Ticket ticket = primaryLockerRobot.store(bag);
        });
    }

    @Test
    public void should_return_package_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_has_valid_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Bag expectBag = new Bag();
        Ticket ticket = freeLocker.store(expectBag);
        lockers.add(freeLocker);

        Assertions.assertEquals(expectBag, primaryLockerRobot.pickUpWith(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_has_invalid_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Ticket ticket = new Ticket();
        lockers.add(freeLocker);

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            primaryLockerRobot.pickUpWith(ticket);
        });
    }

    @Test
    public void should_throw_invalid_ticket_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_a_used_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Bag bag = new Bag();
        Ticket ticket = freeLocker.store(bag);
        freeLocker.pickUpWith(ticket);
        lockers.add(freeLocker);

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            primaryLockerRobot.pickUpWith(ticket);
        });
    }
}
