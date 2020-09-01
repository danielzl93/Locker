package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class LockerTest {
    private int DEFAULT_CAPACITY = 2;
    private Locker locker;
    private ArrayList<Locker> lockers = new ArrayList<>();
    private PrimaryLockerRobot primaryLockerRobot;

    @BeforeEach
    public void init() {
        locker = new Locker(DEFAULT_CAPACITY);
        lockers.add(locker);
        primaryLockerRobot = new PrimaryLockerRobot(lockers);
    }

    @Test
    public void should_return_ticket_when_save_package_given_locker_has_free_capacity() {
        Package pack = new Package();
        Ticket ticket = locker.store(pack);

        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_throw_no_capacity_when_save_package_given_locker_has_no_capacity() {
        Package pack = new Package();
        setFullLocker(locker);

        Assertions.assertThrows(FullCapacityException.class, () -> {
            Ticket ticket = locker.store(pack);
        });
    }

    @Test
    public void should_return_package_when_pick_up_package_given_valid_ticket() {
        Package ExpectPack = new Package();
        Ticket ticket = locker.store(ExpectPack);

        Assertions.assertEquals(ExpectPack, locker.pickUpPackage(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_pick_up_package_given_invalid_ticket() {
        setFullLocker(locker);
        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            locker.pickUpPackage(ticket);
        });
    }

    @Test
    public void should_throw_used_ticket_when_pick_up_package_given_used_ticket() {
        Package pack = new Package();
        Ticket ticket = locker.store(pack);
        locker.pickUpPackage(ticket);

        Assertions.assertThrows(UsedTicketException.class, () -> {
            locker.pickUpPackage(ticket);
        });
    }

    @Test
    public void should_return_ticket_when_primary_locker_robot_save_package_given_robot_manage_single_locker_and_has_free_capacity() {
        Package pack = new Package();
        Ticket ticket = primaryLockerRobot.store(pack);
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_and_save_to_first_free_locker_when_primary_locker_robot_save_package_given_robot_manage_multiple_lockers_and_all_has_free_capacity() {
        Package pack = new Package();

        lockers.add(new Locker(DEFAULT_CAPACITY));

        Ticket ticket = primaryLockerRobot.store(pack);
        Assertions.assertNotNull(ticket);

        Assertions.assertTrue(primaryLockerRobot.getLockers().get(0).getTicketPackageMap().containsKey(ticket));
        Assertions.assertFalse(primaryLockerRobot.getLockers().get(1).getTicketPackageMap().containsKey(ticket));
    }

    @Test
    public void should_return_ticket_and_save_to_first_free_locker_when_primary_locker_robot_save_package_given_robot_manage_multiple_lockers_and_only_the_first_locker_full() {
        setFullLocker(locker);
        lockers.add(new Locker(DEFAULT_CAPACITY));

        Package pack = new Package();
        Ticket ticket = primaryLockerRobot.store(pack);
        Assertions.assertNotNull(ticket);

        Assertions.assertFalse(primaryLockerRobot.getLockers().get(0).getTicketPackageMap().containsKey(ticket));
        Assertions.assertTrue(primaryLockerRobot.getLockers().get(1).getTicketPackageMap().containsKey(ticket));
    }

    @Test
    public void should_throw_full_capacity_when_primary_locker_robot_save_package_given_robot_manage_multiple_lockers_and_all_lockers_full() {
        Locker fullLocker = new Locker(DEFAULT_CAPACITY);
        setFullLocker(locker);
        setFullLocker(fullLocker);
        lockers.add(fullLocker);

        Package pack = new Package();
        Assertions.assertThrows(FullCapacityException.class, () -> {
            Ticket ticket = primaryLockerRobot.store(pack);
        });
    }

    @Test
    public void should_return_package_when_primary_locker_robot_pick_up_package_given_robot_manage_multiple_lockers_and_has_valid_ticket() {
        Package pack = new Package();
        Ticket ticket = locker.store(pack);

        Assertions.assertEquals(pack, primaryLockerRobot.pickUp(ticket));
    }

    private void setFullLocker(Locker locker) {
        Package pack = new Package();
        Ticket ticket_placeholder1 = locker.store(pack);
        Ticket ticket_placeholder2 = locker.store(pack);
    }
}
