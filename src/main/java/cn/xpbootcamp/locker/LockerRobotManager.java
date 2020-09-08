package cn.xpbootcamp.locker;

import java.util.ArrayList;

public class LockerRobotManager extends PrimaryLockerRobot{
    private ArrayList<LockerRobot> robots;
    public LockerRobotManager(ArrayList<Locker> lockers, ArrayList<LockerRobot> robots) {
        super(lockers);
        this.robots = robots;
    }

    protected Ticket storeWith(Bag bag) {
        for (LockerRobot robot : robots) {
            if(!robot.areLockersFull()) {
                return robot.store(bag);
            }
        }
        return super.store(bag);
    }

    protected Bag pickUpWith(Ticket ticket) {
        try {
            for (LockerRobot robot : robots) {
                return robot.pickUp(ticket);
            }
        } catch (InvalidTicketException exception) {
            return super.pickUp(ticket);
        }
        throw new InvalidTicketException();
    }
}
