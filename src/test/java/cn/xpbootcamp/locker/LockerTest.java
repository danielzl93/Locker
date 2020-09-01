package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

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

    //1. given `PrimaryLockerRobot`管理单个`locker`，`locker`有空闲 when `PrimaryLockerRobot`存包 then 返回票据，包裹存放在`locker`中
    @Test
    public void should_return_ticket_when_primary_locker_robot_save_package_given_robot_manage_single_locker_and_has_free_capacity() {
        Package pack = new Package();
        Ticket ticket = primaryLockerRobot.store(pack);
        Assertions.assertNotNull(ticket);
    }

    private void setFullLocker(Locker locker) {
        Package pack = new Package();
        Ticket ticket_placeholder1 = locker.store(pack);
        Ticket ticket_placeholder2 = locker.store(pack);
        this.locker = locker;
    }
}
