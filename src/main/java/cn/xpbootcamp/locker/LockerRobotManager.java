package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Objects;

public class LockerRobotManager implements Storable {
    private final List<Storable> storables;
    public LockerRobotManager(List<Storable> storables) {
        this.storables = storables;
    }
    @Override
    public Ticket store(Bag bag) {
        Storable storableCandidate = null;
        for (Storable storable : storables) {
            if (!storable.isFull() && storable instanceof LockerRobot) {
                return storable.store(bag);
            }
            if (!storable.isFull() && Objects.isNull(storableCandidate)) {
                storableCandidate = storable;
            }
        }
        if (!Objects.isNull(storableCandidate)) {
            return storableCandidate.store(bag);
        }

        throw new FullCapacityException();
    }
    @Override
    public Bag pickUpWith(Ticket ticket) {
        for (Storable storable : storables) {
            Bag bag;
            try {
                bag = storable.pickUpWith(ticket);
            } catch (InvalidTicketException e) {
                continue;
            }
            if (!Objects.isNull(bag)) {
                return bag;
            }
        }

        throw new InvalidTicketException();
    }

    @Override
    public boolean isFull() {
        for (Storable storable : storables) {
                return !storable.isFull();
        }
        return true;
    }
}
