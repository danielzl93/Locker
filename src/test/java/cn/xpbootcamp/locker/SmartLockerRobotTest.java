package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SmartLockerRobotTest {

    private int DEFAULT_CAPACITY = 5;
    private Locker firstLocker;
    private ArrayList<Locker> lockers = new ArrayList<>();
    private SmartLockerRobot smartLockerRobot;

    private void setLockerWithUsedSlot(Locker locker, int usedSlot) {
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Package());
        }
    }

    @BeforeEach
    public void init() {
        firstLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(firstLocker);
        smartLockerRobot = new SmartLockerRobot(lockers);
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_robot_store_package_given_2_lockers_and_1st_locker_has_max_free_slots() {
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);
        setLockerWithUsedSlot(secondLocker, 2);

        Package expectPack = new Package();
        Ticket ticket = smartLockerRobot.store(expectPack);

        Assertions.assertEquals(expectPack, firstLocker.pickUpPackage(ticket));
    }

    @Test
    public void should_return_ticket_and_store_to_2nd_locker_when_robot_store_package_given_2_lockers_and_2nd_locker_has_max_free_slots() {
        Locker secondLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(secondLocker);
        setLockerWithUsedSlot(firstLocker, 3);
        setLockerWithUsedSlot(secondLocker, 1);

        Package expectPack = new Package();
        Ticket ticket = smartLockerRobot.store(expectPack);

        Assertions.assertEquals(expectPack, secondLocker.pickUpPackage(ticket));
    }
}
