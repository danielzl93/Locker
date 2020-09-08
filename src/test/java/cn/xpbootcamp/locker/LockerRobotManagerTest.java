package cn.xpbootcamp.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class LockerRobotManagerTest {
    private ArrayList<LockerRobot> robots = new ArrayList<>();

    private Locker createLockerWithUsedSlot(int usedSlot) {
        int defaultCapacity = 5;
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    private ArrayList<Locker> createLockersWithTwoLockersByUsedSlot(int firstLockerUsedSlot, int secondLockerUsedSlot) {
        ArrayList<Locker> lockers = new ArrayList<>();
        lockers.add(createLockerWithUsedSlot(firstLockerUsedSlot));
        lockers.add(createLockerWithUsedSlot(secondLockerUsedSlot));
        return lockers;
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_manager_store_bag_given_2_lockers_no_robot_all_locker_have_free_slots() {
        ArrayList<Locker> lockers = createLockersWithTwoLockersByUsedSlot(3, 2);
        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = manager.storeWith(bag);

        Assertions.assertEquals(bag, lockers.get(0).pickUpBag(ticket));
    }

    @Test
    public void should_return_ticket_and_store_to_2nd_locker_when_manager_store_bag_given_2_lockers_no_robot_1st_locker_full_and_2nd_locker_has_free_slots() {
        ArrayList<Locker> lockers = createLockersWithTwoLockersByUsedSlot(5, 2);

        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = manager.storeWith(bag);

        Assertions.assertEquals(bag, lockers.get(1).pickUpBag(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_2_lockers_no_robot_and_all_locker_have_no_free_slots() {
        ArrayList<Locker> lockers = createLockersWithTwoLockersByUsedSlot(5, 5);
        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class,() -> {
            manager.storeWith(bag);
        });
    }

    @Test
    public void should_return_ticket_and_1st_robot_store_bag_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_all_locker_have_free_slots() {
        ArrayList<Locker> firstLockers = createLockersWithTwoLockersByUsedSlot(2, 3);
        ArrayList<Locker> secondLockers = createLockersWithTwoLockersByUsedSlot(2, 3);

        robots.add(new PrimaryLockerRobot(firstLockers));
        robots.add(new SmartLockerRobot(secondLockers));
        ArrayList<Locker> lockers = new ArrayList<>();
        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = manager.storeWith(bag);

        Assertions.assertEquals(bag, robots.get(0).pickUp(ticket));
    }

    @Test
    public void should_return_ticket_and_2nd_robot_store_bag_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_first_robot_locker_has_no_free_slots() {
        ArrayList<Locker> firstLockers = createLockersWithTwoLockersByUsedSlot(5, 5);
        ArrayList<Locker> secondLockers = createLockersWithTwoLockersByUsedSlot(2, 3);

        robots.add(new PrimaryLockerRobot(firstLockers));
        robots.add(new SmartLockerRobot(secondLockers));
        ArrayList<Locker> lockers = new ArrayList<>();
        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = manager.storeWith(bag);

        Assertions.assertEquals(bag, robots.get(1).pickUp(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_all_robot_locker_has_no_free_slots() {
        ArrayList<Locker> firstLockers = createLockersWithTwoLockersByUsedSlot(5, 5);
        ArrayList<Locker> secondLockers = createLockersWithTwoLockersByUsedSlot(5, 5);

        robots.add(new PrimaryLockerRobot(firstLockers));
        robots.add(new SmartLockerRobot(secondLockers));
        ArrayList<Locker> lockers = new ArrayList<>();
        LockerRobotManager manager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class,() -> {
            manager.storeWith(bag);
        });
    }

    @Test
    public void should_return_ticket_and_robot_store_bag_when_manager_store_bag_given_1_robots_and_manager_manage_1_locker_and_all_lockers_have_free_slots() {
        ArrayList<Locker> firstLockers = createLockersWithTwoLockersByUsedSlot(2, 3);
        ArrayList<Locker> secondLockers = createLockersWithTwoLockersByUsedSlot(2, 3);

        robots.add(new PrimaryLockerRobot(firstLockers));
        LockerRobotManager manager = new LockerRobotManager(secondLockers, robots);
        Bag bag = new Bag();
        Ticket ticket = manager.storeWith(bag);

        Assertions.assertEquals(bag, robots.get(0).pickUp(ticket));
        Assertions.assertThrows(InvalidTicketException.class, () -> {
            secondLockers.get(0).pickUpBag(ticket);
        });
    }
}
