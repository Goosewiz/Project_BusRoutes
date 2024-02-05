package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

public class BusStopsManager {
    @Getter
    private final List<BusStop> busStopsList = new ArrayList<>();

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
        List<FullRoute> tempList = new ArrayList<>();
        if (busStop != null) {
            List<FullRoute> fullRouteList = FullRouteManager.getFullRouteList();
            for(FullRoute fullRoute: fullRouteList){
                List<BusAtStop> busAtStopList = fullRoute.getBusAtStopList();
                for(BusAtStop busAtStop: busAtStopList){
                    if (busAtStop.getBusStop().getTitle().equals(title)){
                        tempList.add(fullRoute);
                        break;
                    }
                }
            }
            FullRoute[] answer = new FullRoute[tempList.size()];
            tempList.toArray(answer);
            return answer;
        }
        return null;
    }

    FullRoute[] getAllRoutesBetween(String title1, String title2) {
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        List<FullRoute> tempList1 = new ArrayList<>();
        List<FullRoute> tempList2 = new ArrayList<>();
        if (busStop1 != null && busStop2 != null) {
            List<FullRoute> fullRouteList = FullRouteManager.getFullRouteList();
            for(FullRoute fullRoute: fullRouteList){
                List<BusAtStop> busAtStopList = fullRoute.getBusAtStopList();
                for(BusAtStop busAtStop: busAtStopList){
                    if (busAtStop.getBusStop().getTitle().equals(title1)){
                        tempList1.add(fullRoute);
                    }
                    if (busAtStop.getBusStop().getTitle().equals(title2)){
                        tempList2.add(fullRoute);
                    }
                }
            }
            tempList1.retainAll(tempList2);
            FullRoute[] answer = new FullRoute[tempList1.size()];
            tempList1.toArray(answer);
            return answer;
        }
        return null;
    }
}