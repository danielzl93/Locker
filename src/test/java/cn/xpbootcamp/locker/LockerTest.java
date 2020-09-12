package cn.xpbootcamp.locker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class LockerTest {
    private int DEFAULT_CAPACITY = 2;
    private Locker locker;

    @BeforeEach
    public void init() {
        locker = new Locker(DEFAULT_CAPACITY);
    }

    private void setFullLocker(Locker locker) {
        Bag bag = new Bag();
        Ticket ticket_placeholder1 = locker.store(bag);
        Ticket ticket_placeholder2 = locker.store(bag);
    }

    @Test
    public void should_return_ticket_when_save_package_given_locker_has_free_capacity() {
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);

        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_throw_no_capacity_when_save_package_given_locker_has_no_capacity() {
        Bag bag = new Bag();
        setFullLocker(locker);

        Assertions.assertThrows(FullCapacityException.class, () -> {
            Ticket ticket = locker.store(bag);
        });
    }

    @Test
    public void should_return_package_when_pick_up_package_given_valid_ticket() {
        Bag expectBag = new Bag();
        Ticket ticket = locker.store(expectBag);

        Assertions.assertEquals(expectBag, locker.pickUpWith(ticket));
    }

    @Test
    public void should_throw_invalid_ticket_when_pick_up_package_given_invalid_ticket() {
        setFullLocker(locker);
        Ticket ticket = new Ticket();

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            locker.pickUpWith(ticket);
        });
    }

    @Test
    public void should_throw_invalid_ticket_when_pick_up_package_given_used_ticket() {
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);
        locker.pickUpWith(ticket);

        Assertions.assertThrows(InvalidTicketException.class, () -> {
            locker.pickUpWith(ticket);
        });
    }
}
