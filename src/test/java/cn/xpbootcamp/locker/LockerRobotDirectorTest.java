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
        String expectedReport = "M 3 10\n  L 0 5\n  L 3 5\n";
        String report = director.createReport();

        assertEquals(report, expectedReport);
    }

    @Test
    public void should_return_report_when_summarise_statistics_given_1_manager_manages_1_lockers_and_1_robot_with_2_lockers() {
        Locker firstLocker = createLockerWithUsedSlot(4, 8);
        Locker robotFirstLocker = createLockerWithUsedSlot(2, 5);
        Locker robotSecondLocker = createLockerWithUsedSlot(3, 6);
        LockerRobotManager manager = new LockerRobotManager(asList(
                firstLocker,
                new PrimaryLockerRobot(asList(
                        robotFirstLocker,
                        robotSecondLocker)
                )));

        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String expectedReport = "M 10 19\n  L 4 8\n  R 6 11\n\tL 3 5\n\tL 3 6\n";
        String report = director.createReport();

        assertEquals(expectedReport, report);
    }

    @Test
    public void should_return_report_when_summarise_statistics_given_1_manager_manages_1st_robot_with_2_lockers_and_2nd_robot_with_3_lockers() {
        Locker firstRobotFirstLocker = createLockerWithUsedSlot(2, 5);
        Locker firstRobotSecondLocker = createLockerWithUsedSlot(7, 11);
        Locker secondRobotFirstLocker = createLockerWithUsedSlot(3, 9);
        Locker secondRobotSecondLocker = createLockerWithUsedSlot(8, 8);
        Locker secondRobotThirdLocker = createLockerWithUsedSlot(2, 4);

        LockerRobotManager manager = new LockerRobotManager(asList(
                new SmartLockerRobot(asList(firstRobotFirstLocker, firstRobotSecondLocker)),
                new PrimaryLockerRobot(asList(secondRobotFirstLocker, secondRobotSecondLocker, secondRobotThirdLocker))
        ));

        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String expectedReport = "M 15 37\n  R 7 16\n\tL 3 5\n\tL 4 11\n  R 8 21\n\tL 6 9\n\tL 0 8\n\tL 2 4\n";
        String report = director.createReport();

        assertEquals(expectedReport, report);
    }
}
