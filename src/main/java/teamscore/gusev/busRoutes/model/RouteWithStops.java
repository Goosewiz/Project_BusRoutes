package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RouteWithStops {
    @Getter
    @Setter
    @NonNull
    private final List<BusAtStop> busAtStopList;
    @Getter
    @Setter
    @NonNull
    private Route route;

    public boolean isUsed(BusStop busStop) {
        Stream<BusAtStop> stream = busAtStopList.stream();
        boolean answer = stream.anyMatch(e -> e.getBusStop() == busStop);
        return answer;
    }

    public void addBusAtStop(BusAtStop busAtStop) {
        BusStop busStop = busAtStop.getBusStop();
        if (!isUsed(busStop)) {
            busAtStopList.add(busAtStop);
            busStop.addRoute(this.getRoute());
        }
    }

    public void deleteBusAtStop(BusAtStop busAtStop) {
        BusStop busStop = busAtStop.getBusStop();
        busAtStopList.remove(busAtStop);
        busStop.removeRoute(this.getRoute());
    }

    public RouteWithStops reverseRouteWithStops() {
        Route routeNew = new Route(route.getNumber(), "обратный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        LinkedList<BusAtStop> listNew = new LinkedList<>(busAtStopList);
        Collections.reverse(listNew);
        RouteWithStops routeWithStopsNew = new RouteWithStops(listNew, routeNew);
        return routeWithStopsNew;
    }

    public RouteWithStops copyRouteWithStops(){
        Route routeNew = new Route(route.getNumber(), "скопированный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        LinkedList<BusAtStop> listNew = new LinkedList<>(busAtStopList);
        RouteWithStops routeWithStopsNew = new RouteWithStops(listNew, routeNew);
        return routeWithStopsNew;
    }
    public LocalTime getTimeByBusStop(BusStop busStop){
        Stream<BusAtStop> stream = busAtStopList.stream();
        Optional busAtStop = stream.filter(e -> e.getBusStop() == busStop)
                .findFirst();
        if (busAtStop == null)
            throw  new RuntimeException("Нет такой остановки");
        BusAtStop temp = (BusAtStop)busAtStop.get();
        LocalTime answer = route.getTimeStart();
        answer = answer.plusMinutes(temp.getTime());
        return answer;
    }
}
