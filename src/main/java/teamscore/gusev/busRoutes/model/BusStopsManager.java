package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BusStopsManager {
    @Getter
    private static List<BusStop> busStopsList;

    BusStopsManager() {
        busStopsList = new ArrayList<BusStop>();
    }

    void addBusStop(BusStop busStop) {
        busStopsList.add(busStop);
    }

    void editBusStop(BusStop busStopOld, BusStop busStopNew) {
        int i = busStopsList.indexOf(busStopOld);
        if (i != -1) {
            busStopsList.set(i, busStopNew);
        }
    }

    void deleteBusStop(BusStop busStop) {
        busStopsList.remove(busStop);
    }

    BusStop findBusStop(String title) {
        int i = 0;
        BusStop busStop;
        while (i < busStopsList.size()) {
            busStop = busStopsList.get(i);
            if (busStop.getTitle().equals(title)) {
                return busStop;
            }
            i++;
        }
        return null;
    }

    Set<Route> getAllRoutes(String title) {
        Set<Route> answer = new HashSet<>();
        BusStop busStop = findBusStop(title);
        if (busStop != null){
            List<Route> allRoutes = RoutesManager.getRoutesList();
            int j = 0;
            while (j < allRoutes.size()) {
                Route route = allRoutes.get(j);
                FullRoute fullRoute = route.getFullRoute();
                if (fullRoute.getBusStopsList().contains(busStop)) {
                    answer.add(route);
                }
            }
            return answer;
        }
        return answer;
    }

    Set<Route> getAllRoutesBetween(String title1, String title2) {
        Set<Route> answer = new HashSet<>();
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        if (busStop1 != null && busStop2 != null){
            List<Route> allRoutes = RoutesManager.getRoutesList();
            int j = 0;
            while (j < allRoutes.size()) {
                Route route = allRoutes.get(j);
                FullRoute fullRoute = route.getFullRoute();
                if (fullRoute.getBusStopsList().contains(busStop1) && fullRoute.getBusStopsList().contains(busStop2)) {
                    int index1 = fullRoute.getBusStopsList().indexOf(busStop1);
                    int index2 = fullRoute.getBusStopsList().indexOf(busStop2);
                    if (index1 < index2)
                        answer.add(route);
                }
            }
            return answer;
        }
        return answer;
    }
}