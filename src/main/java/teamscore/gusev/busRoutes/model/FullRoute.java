package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.time.LocalTime;
import java.util.LinkedList;

public class FullRoute {
    @Getter
    private LinkedList<BusStop> busStopsList;
    @Getter
    private LinkedList<LocalTime> timeToReachBusStop;

    FullRoute() {
        busStopsList = new LinkedList<>();
        timeToReachBusStop = new LinkedList<>();
    }

    boolean isUsed(BusStop busStop) {
        return (busStopsList.contains(busStop));
    }

    void addInfo(BusStop busStop, LocalTime time) {
        if (!isUsed(busStop)) {
            busStopsList.add(busStop);
            timeToReachBusStop.add(time);
        }
    }

    void editInfo(BusStop busStop, LocalTime time) {
        int i = busStopsList.indexOf(busStop);
        if (i != -1)
            timeToReachBusStop.set(i, time);
    }

    void deleteInfo(BusStop busStop) {
        int i = busStopsList.indexOf(busStop);
        busStopsList.remove(busStop);
        if (i != -1)
            timeToReachBusStop.remove(i);
    }
}
