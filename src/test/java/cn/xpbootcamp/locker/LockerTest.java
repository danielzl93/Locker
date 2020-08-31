package cn.xpbootcamp.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class LockerTest {
    private int DEFAULT_CAPACITY = 2;
    private Locker locker = new Locker(DEFAULT_CAPACITY);

    @BeforeEach
    public void init() {
        locker = new Locker(DEFAULT_CAPACITY);
    }

    @Test
    public void should_return_ticket_when_save_package_given_locker_has_free_capacity() {
        Package pack = new Package();
        Ticket ticket = locker.store(pack);

        Assertions.assertNotNull(ticket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_no_capacity_when_save_package_given_locker_has_no_capacity() {
        Package pack = new Package();
        setFullLocker(locker);
        Ticket ticket = locker.store(pack);
    }

    @Test
    public void should_return_package_when_pick_up_package_given_valid_ticket() {
        Package ExpectPack = new Package();
        Ticket ticket = locker.store(ExpectPack);

        Assertions.assertEquals(ExpectPack, locker.pickUpPackage(ticket));
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_invalid_ticket_when_pick_up_package_given_invalid_ticket() {
        setFullLocker(locker);
        Ticket ticket = new Ticket();
        locker.pickUpPackage(ticket);
    }

    @Test(expected = UsedTicketException.class)
    public void should_throw_used_ticket_when_pick_up_package_given_used_ticket() {
        Package pack = new Package();
        Ticket ticket = locker.store(pack);
        locker.pickUpPackage(ticket);

        locker.pickUpPackage(ticket);
    }

    private void setFullLocker(Locker locker) {
        Package pack = new Package();
        Ticket ticket_placeholder1 = locker.store(pack);
        Ticket ticket_placeholder2 = locker.store(pack);
        this.locker = locker;
    }
}
