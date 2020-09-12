package cn.xpbootcamp.locker;

import org.junit.Test;
import java.util.Collections;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class LockerRobotDirectorTest {
    private Locker createLockerWithUsedSlot(int usedSlot, int defaultCapacity) {
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < usedSlot; i++) {
            locker.store(new Bag());
        }
        return locker;
    }

    @Test
    public void should_return_report_when_summarise_statistics_given_1_manager_manages_2_lockers_locker1_has_0_out_of_5_capacity_and_locker2_has_3_out_of_five() {
        Locker firstLocker = createLockerWithUsedSlot(5, 5);
        Locker secondLocker = createLockerWithUsedSlot(2, 5);
        LockerRobotManager manager = new LockerRobotManager(asList(firstLocker,secondLocker));

        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String expectedReport = "M 3 10\n\tL 0 5\n\tL 3 5\n";
        String report = director.createReport();

        assertEquals(report, expectedReport);
    }
}
