package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;

@RequiredArgsConstructor
public class FullRoute {
    @Getter
    @Setter
    @NonNull
    private LinkedList<BusAtStop> busAtStopList;//final
    @Getter
    @Setter
    @NonNull
    private Route route;

    boolean isUsed(BusAtStop busAtStop) {
        return (busAtStopList.contains(busAtStop));
    }

    void addBusAtStop(BusAtStop busAtStop) {
        if (!isUsed(busAtStop)) {
            busAtStopList.add(busAtStop);
        }
    }

    void editBusAtStop(BusAtStop busAtStop, int position) {
        if (busAtStopList.size() > position)
            busAtStopList.set(position, busAtStop);
    }

    void deleteBusAtStop(BusAtStop busAtStop) {
        busAtStopList.remove(busAtStop);
    }

   /* boolean findFullRouteByStop(String title){
        for(BusAtStop busAtStop: busAtStopList){
            if (busAtStop.getBusStop().getTitle() == title)
                return true;
        }
        return false;
    }*/

    FullRoute reverseFullRoute() {
        Route routeNew = new Route(route.getNumber(), "обратный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        LinkedList<BusAtStop> listNew = new LinkedList<>(busAtStopList);
        Collections.reverse(listNew);
        FullRoute fullRouteNew = new FullRoute(listNew, routeNew);
        FullRouteManager.addFullRoute(fullRouteNew);
        return fullRouteNew;
    }

    FullRoute copyFullRoute(){
        Route routeNew = new Route(route.getNumber(), "скопированный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        LinkedList<BusAtStop> listNew = new LinkedList<>(busAtStopList);
        Collections.reverse(listNew);
        FullRoute fullRouteNew = new FullRoute(listNew, routeNew);
        FullRouteManager.addFullRoute(fullRouteNew);
        return fullRouteNew;
    }
}
