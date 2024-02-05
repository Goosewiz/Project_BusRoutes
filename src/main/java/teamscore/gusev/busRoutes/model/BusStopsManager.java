package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

public class BusStopsManager {
    @Getter
    private static final List<BusStop> busStopsList = new ArrayList<>();

    void addBusStop(BusStop busStop) {
        if (!busStopsList.contains(busStop))
            busStopsList.add(busStop);
    }

    void editBusStop(BusStop busStop, int position) {
        if (busStopsList.size() > position)
            busStopsList.set(position, busStop);
    }

    void deleteBusStop(BusStop busStop) {
        busStopsList.remove(busStop);
    }

    BusStop findBusStop(String title) {
        Stream<BusStop> stream = busStopsList.stream();
        Optional busStop = stream.filter(p -> p.getTitle() == title)
                .findFirst();
        BusStop answer = (BusStop) busStop.get();
        return answer;
    }

    FullRoute[] getAllRoutes(String title) {
        BusStop busStop = findBusStop(title);
        List<FullRoute> answerB;
        if (busStop != null) {
            List<FullRoute> fullRouteList = FullRouteManager.getFullRouteList();
            Stream<FullRoute> stream = fullRouteList.stream();
            answerB = stream.filter(p -> p.getBusAtStopList().contains(busStop))
                    .toList();
            FullRoute[] answer = (FullRoute[]) answerB.toArray();
            return answer;
        }
        return null;
    }

    FullRoute[] getAllRoutesBetween(String title1, String title2) {
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        List<FullRoute> answerB;
        if (busStop1 != null && busStop2 != null) {
            List<FullRoute> fullRouteList = FullRouteManager.getFullRouteList();
            Stream<FullRoute> stream = fullRouteList.stream();
            answerB = stream.filter(p -> (p.getBusAtStopList().contains(busStop1) && p.getBusAtStopList().contains(busStop2)))
                    .toList();
            FullRoute[] answer = (FullRoute[]) answerB.toArray();
            return answer;
        }
        return null;
    }
}