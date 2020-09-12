package cn.xpbootcamp.locker;

import java.util.List;

public class LockerRobotDirector {
    private List<LockerRobotManager> lockerRobotManagers;

    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManagers) {
        this.lockerRobotManagers = lockerRobotManagers;
    }


    public String createReport() {
        StringBuilder builder = new StringBuilder();
        for (LockerRobotManager lockerRobotManager : lockerRobotManagers) {
            builder.append(lockerRobotManager.createReport());
        }
        return builder.toString();
    }
}
