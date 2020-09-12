package cn.xpbootcamp.locker;

import com.sun.tools.javac.code.Scope;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static java.util.Arrays.asList;

public class LockerRobotManagerTest {
    int defaultCapacity = 5;

    private Locker createLockerWithUsedSlot(int usedSlot) {
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    @Test
    public void should_return_ticket_and_store_to_1st_locker_when_manager_store_bag_given_2_lockers_no_robot_all_locker_have_free_slots() {
        Locker firstLocker = createLockerWithUsedSlot(2);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, new Locker(3)));
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, firstLocker.pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_store_to_2nd_locker_when_manager_store_bag_given_2_lockers_no_robot_1st_locker_full_and_2nd_locker_has_free_slots() {
        Locker secondLocker = createLockerWithUsedSlot(2);
        LockerRobotManager manager = new LockerRobotManager(asList(createLockerWithUsedSlot(5), secondLocker));
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, secondLocker.pickUpWith(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_2_lockers_no_robot_and_all_locker_have_no_free_slots() {
        LockerRobotManager manager = new LockerRobotManager(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5)));
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class,() -> {
            manager.store(bag);
        });
    }

    @Test
    public void should_return_ticket_and_1st_robot_store_bag_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_all_locker_have_free_slots() {
        List<Storable> robots = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))),
                new SmartLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(robots);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, robots.get(0).pickUpWith(ticket));
    }

    @Test
    public void should_return_ticket_and_2nd_robot_store_bag_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_first_robot_locker_has_no_free_slots() {
        List<Storable> robots = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))),
                new SmartLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(robots);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, robots.get(1).pickUpWith(ticket));
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_2_robots_and_manager_not_manager_locker_and_all_robot_locker_has_no_free_slots() {
        List<Storable> robots = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))),
                new SmartLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(robots);
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class,() -> {
            manager.store(bag);
        });
    }

    @Test
    public void should_return_ticket_and_robot_store_bag_when_manager_store_bag_given_1_robots_and_manager_manage_1_locker_and_all_lockers_have_free_slots() {
        List<Storable> storables = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))),
                createLockerWithUsedSlot(3));

        LockerRobotManager manager = new LockerRobotManager(storables);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, storables.get(0).pickUpWith(ticket));
        Assertions.assertThrows(InvalidTicketException.class, () -> {
            storables.get(1).pickUpWith(ticket);
        });
    }

    @Test
    public void should_return_ticket_and_bag_store_to_manager_locker_when_manager_store_bag_given_1_robots_and_manager_manage_1_locker_and_robot_lockers_have_no_free_slots() {
        List<Storable> storables = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))),
                createLockerWithUsedSlot(3));

        LockerRobotManager manager = new LockerRobotManager(storables);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, storables.get(1).pickUpWith(ticket));
        Assertions.assertThrows(InvalidTicketException.class, () -> {
            storables.get(0).pickUpWith(ticket);
        });
    }

    @Test
    public void should_throw_no_capacity_when_manager_store_bag_given_1_robots_and_manager_manage_1_locker_and_all_lockers_have_no_free_slots() {
        List<Storable> storables = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))),
                createLockerWithUsedSlot(5));

        LockerRobotManager manager = new LockerRobotManager(storables);
        Bag bag = new Bag();

        Assertions.assertThrows(FullCapacityException.class, () -> {
            manager.store(bag);
        });
    }

    @Test
    public void should_get_bag_when_manager_pick_up_bag_given_0_robots_and_manager_manage_2_locker_and_valid_ticket() {
        Locker firstLocker = createLockerWithUsedSlot(2);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, new Locker(3)));
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, manager.pickUpWith(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_manager_pick_up_bag_given_0_robots_and_manager_manage_2_locker_and_invalid_ticket() {
        Locker firstLocker = createLockerWithUsedSlot(2);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker, new Locker(3)));
        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            manager.pickUpWith(ticket);
        });
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_2_robots_and_manager_manage_0_locker_and_valid_ticket() {
        List<Storable> robots = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))),
                new SmartLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(robots);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, manager.pickUpWith(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_manager_pick_up_bag_given_2_robots_and_manager_manage_0_locker_and_invalid_ticket() {
        List<Storable> robots = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))),
                new SmartLockerRobot(asList(createLockerWithUsedSlot(2), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(robots);
        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            manager.pickUpWith(ticket);
        });
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_1_robots_and_manager_manage_1_locker_and_valid_ticket() {
        List<Storable> storables = asList(createLockerWithUsedSlot(3),
                new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))));

        LockerRobotManager manager = new LockerRobotManager(storables);
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Assertions.assertEquals(bag, manager.pickUpWith(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_manager_pick_up_bag_given_1_robots_and_manager_manage_1_locker_and_invalid_ticket() {
        List<Storable> storables = asList(new PrimaryLockerRobot(asList(createLockerWithUsedSlot(5), createLockerWithUsedSlot(5))),
                createLockerWithUsedSlot(3));

        LockerRobotManager manager = new LockerRobotManager(storables);
        Bag bag = new Bag();
        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            manager.pickUpWith(ticket);
        });
    }
}
