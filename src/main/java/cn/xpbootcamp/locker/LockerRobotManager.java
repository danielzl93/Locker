package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class LockerRobotManager extends PrimaryLockerRobot{
    private ArrayList<LockerRobot> robots;
    public LockerRobotManager(ArrayList<Locker> lockers, ArrayList<LockerRobot> robots) {
        super(lockers);
        this.robots = robots;
    }

    protected Ticket storeWith(Bag bag) {
        if (robots.isEmpty()) {
            return super.store(bag);
        }
        for (LockerRobot robot : robots) {
            if(!robot.areLockersFull()) {
                return robot.store(bag);
            }
        }
        throw new FullCapacityException();

    }
}
