package cn.xpbootcamp.locker;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class LockerTest {
    @Test
    public void should_return_valid_ticket_form_when_locker_has_capacity() {
        String ticketRegex = "barcode_\\d{1,2}";
        int capacity = 10;
        Locker locker = new Locker(capacity);

        String ticket = locker.store();
        Assert.assertTrue(ticket.matches(ticketRegex));
    }

    @Test
    public void should_return_1_not_issued_ticket_when_locker_has_capacity() {
        ArrayList<String> issuedTickets = new ArrayList<>();
        issuedTickets.add("barcode_1");
        int capacity = 10;
        Locker locker = new Locker(capacity, new ArrayList<>(issuedTickets));

        String ticket = locker.store();
        Assert.assertFalse(issuedTickets.contains(ticket));
    }

    @Test
    public void should_capacity_decrease_1_when_locker_has_capacity() {
        ArrayList<String> issuedTickets = new ArrayList<>();
        int capacity = 10;
        Locker locker = new Locker(capacity, new ArrayList<>(issuedTickets));
        String ticket = locker.store();
        Assert.assertEquals(capacity - 1, locker.getCapacity());
    }

    @Test
    public void should_return_locker_full_and_capacity_is_0_when_locker_has_no_capacity() {
        String expectFeedback = "no capacity";
        Locker locker = new Locker(0);
        try {
            String ticket = locker.store();
        }catch (Exception e) {
            Assert.assertEquals(expectFeedback, e.getMessage());
        }
    }

    @Test
    public void should_return_pick_up_success_and_capacity_add_1_when_provide_valid_ticket() {
        String ticket = "barcode_1";
        ArrayList<String> issuedTickets = new ArrayList<>();
        issuedTickets.add(ticket);

        String expectFeedback = "success";
        int capacity = 10;
        Locker locker = new Locker(capacity, new ArrayList<>(issuedTickets));

        String feedback = locker.pickUp(ticket);
        Assert.assertEquals(expectFeedback, feedback);
        Assert.assertEquals(capacity + 1, locker.getCapacity());
    }

    @Test
    public void should_return_pick_up_failure_when_provide_used_ticket() {
        String ticket = "barcode_1";
        ArrayList<String> issuedTickets = new ArrayList<>();
        ArrayList<String> expiredTickets = new ArrayList<>();
        issuedTickets.add(ticket);
        expiredTickets.add(ticket);

        String expectFeedback = "invalid ticket";
        int capacity = 10;
        Locker locker = new Locker(capacity, new ArrayList<>(issuedTickets), new ArrayList<>(expiredTickets));
        try {
            String feedback = locker.pickUp(ticket);
        } catch (Exception e) {
            Assert.assertEquals(expectFeedback, e.getMessage());
        }
    }

    @Test
    public void should_return_pick_up_failure_when_provide_invalid_ticket() {
        String ticket = "barcode_1";
        ArrayList<String> issuedTickets = new ArrayList<>();
        issuedTickets.add(ticket);

        String expectFeedback = "invalid ticket";
        int capacity = 10;
        Locker locker = new Locker(capacity, issuedTickets);
        try {
            String feedback = locker.pickUp(ticket);
        } catch (Exception e) {
            Assert.assertEquals(expectFeedback, e.getMessage());
        }
    }
}
