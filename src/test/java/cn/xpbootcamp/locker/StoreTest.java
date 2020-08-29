package cn.xpbootcamp.locker;

import org.junit.Assert;
import org.junit.Test;

public class StoreTest {
    @Test
    public void should_return_ticket_when_locker_has_capacity() {
        String expectTicket = "barcode_01";
        Locker locker = new Locker(10);

        String ticket = locker.store();
        Assert.assertEquals(expectTicket, ticket);
    }
}
