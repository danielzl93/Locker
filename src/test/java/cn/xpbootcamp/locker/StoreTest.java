package cn.xpbootcamp.locker;

import org.junit.Assert;
import org.junit.Test;

public class StoreTest {
    @Test
    public void should_return_ticket_and_capacity_decrease_1_when_locker_has_capacity() {
        String expectTicket = "barcode_01";
        int capacity = 10;
        Locker locker = new Locker(capacity);

        String ticket = locker.store();
        Assert.assertEquals(expectTicket, ticket);
        Assert.assertEquals(capacity - 1, locker.getCapacity());
    }

    @Test
    public void should_return_locker_full_and_capacity_is_0_when_locker_has_no_capacity() {
        String expectTicket = "no capacity";

        Locker locker = new Locker(0);

        String ticket = locker.store();
        Assert.assertEquals(expectTicket, ticket);
        Assert.assertEquals(0, locker.getCapacity());
    }
}
