package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class PrimaryLockerRobotTest {
    private int DEFAULT_CAPACITY = 2;
    private Locker firstLocker;
    private ArrayList<Locker> lockers = new ArrayList<>();
    private PrimaryLockerRobot primaryLockerRobot;

    private void setFullLocker(Locker locker) {
        Package pack = new Package();
        Ticket ticket_placeholder1 = locker.store(pack);
        Ticket ticket_placeholder2 = locker.store(pack);
    }

    @BeforeEach
    public void init() {
        firstLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(firstLocker);
        primaryLockerRobot = new PrimaryLockerRobot(lockers);
    }

    @Test
    public void should_return_ticket_when_robot_save_package_given_robot_manage_single_locker_and_has_free_capacity() {
        Package expectPack = new Package();
        Ticket ticket = primaryLockerRobot.store(expectPack);

        Assertions.assertTrue(firstLocker.contains(ticket));
        Assertions.assertEquals(expectPack, firstLocker.pickUpPackage(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_first_locker_when_save_package_given_robot_manage_multiple_lockers_all_has_space() {
        Package expectPack = new Package();
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);
        Ticket ticket = primaryLockerRobot.store(expectPack);

        Assertions.assertEquals(expectPack, firstLocker.pickUpPackage(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_first_free_locker_when_robot_save_package_given_robot_manage_multiple_lockers_and_first_locker_full() {
        setFullLocker(firstLocker);
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);

        Package expectPack = new Package();
        Ticket ticket = primaryLockerRobot.store(expectPack);

        Assertions.assertFalse(firstLocker.contains(ticket));
        Assertions.assertEquals(expectPack, secondLocker.pickUpPackage(ticket));
    }

    @Test
    public void should_throw_full_capacity_robot_save_package_given_robot_manage_multiple_lockers_and_all_lockers_full() {
        Locker fullLocker = new Locker(DEFAULT_CAPACITY);
        setFullLocker(firstLocker);
        setFullLocker(fullLocker);
        lockers.add(fullLocker);

        Package pack = new Package();
        Assertions.assertThrows(FullCapacityException.class, () -> {
            Ticket ticket = primaryLockerRobot.store(pack);
        });
    }

    @Test
    public void should_return_package_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_has_valid_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Package expectPack = new Package();
        Ticket ticket = freeLocker.store(expectPack);
        lockers.add(freeLocker);

        Assertions.assertEquals(expectPack, primaryLockerRobot.pickUp(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_has_invalid_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Package expectPack = new Package();
        Ticket ticket = new Ticket();
        lockers.add(freeLocker);

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            primaryLockerRobot.pickUp(ticket);
        });
    }

    @Test
    public void should_throw_invalid_ticket_when_robot_pick_up_package_given_robot_manage_multiple_lockers_and_a_used_ticket() {
        setFullLocker(firstLocker);
        Locker freeLocker = new Locker(DEFAULT_CAPACITY);
        Package pack = new Package();
        Ticket ticket = freeLocker.store(pack);
        freeLocker.pickUpPackage(ticket);
        lockers.add(freeLocker);

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            primaryLockerRobot.pickUp(ticket);
        });
    }
}
