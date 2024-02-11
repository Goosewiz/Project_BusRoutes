package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Stream;

public class BusStopsManager {
    @Getter
    private final List<BusStop> busStopsList = new ArrayList<>();

    void addBusStop(BusStop busStop) {
        if (!busStopsList.contains(busStop))
            busStopsList.add(busStop);
    }

    void deleteBusStop(BusStop busStop) {
        if (busStop.getRoutesSet().size() == 0)
            busStopsList.remove(busStop);
    }

    BusStop findBusStop(String title) {
        Stream<BusStop> stream = busStopsList.stream();
        Optional busStop = stream.filter(p -> p.getTitle() == title)
                .findFirst();
        BusStop answer = (BusStop) busStop.get();
        if (answer == null)
            throw new RuntimeException("Нет такой остановки");
        return answer;
    }

    Route[] getAllRoutesBetween(String title1, String title2) {
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        Route[] answer = getAllRoutesBetween(busStop1, busStop2);
        return answer;
    }

    Route[] getAllRoutesBetween(@NonNull BusStop busStop1, @NonNull BusStop busStop2) {
        Set<Route> temp = busStop1.getRoutesSet();
        temp.retainAll(busStop2.getRoutesSet());
        Route[] answer = new Route[temp.size()];
        answer = temp.toArray(answer);
        return answer;
    }
}