package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route", schema = "route")
public class RouteWithStops {
    @Getter
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    @NonNull
    @OneToMany(mappedBy = "pk.routeWithStops", cascade = CascadeType.ALL)
    private List<BusAtStop> busAtStopList;
    @Embedded
    @Getter
    @Setter
    @NonNull
    private Route route;

    public boolean isUsed(BusStop busStop) {
        List<BusStop> list = new LinkedList<>();
        for (BusAtStop busAtStop: busAtStopList){
            list.add(busAtStop.getBusStop());
        }
        Stream<BusStop> stream = list.stream();
        boolean answer = stream.anyMatch(e -> e == busStop);
        return answer;
    }

    public void addBusAtStop(BusAtStop busAtStop) {
        BusStop busStop = busAtStop.getPk().getBusStop();
        if (!isUsed(busStop)) {
            busAtStopList.add(busAtStop);
            busStop.addRoute(busAtStop);
        }
    }

    public void removeBusAtStop(BusAtStop busAtStop) {
        BusStop busStop = busAtStop.getPk().getBusStop();
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
        Optional busAtStop = stream.filter(e -> e.getPk().getBusStop() == busStop)
                .findFirst();
        if (busAtStop == null)
            throw  new RuntimeException("Нет такой остановки");
        BusAtStop temp = (BusAtStop)busAtStop.get();
        LocalTime answer = route.getTimeStart();
        answer = answer.plusMinutes(temp.getTime());
        return answer;
    }
}
