package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RoutesManager {
    @Getter
    private static List<Route> routesList;

    RoutesManager(){
        routesList = new ArrayList<>();
    }

    void addRoute(Route route) {
        routesList.add(route);
    }

    void editRoute(Route routeOld, Route routeNew) {
        int i = routesList.indexOf(routeOld);
        if (i != -1) {
            routesList.set(i, routeNew);
        }
    }

    void deleteRoute(Route route) {
        routesList.remove(route);
    }

    Route reverseRoute(Route route) {
        Route routeNew = new Route(route.getNumber(), "обратный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        FullRoute fullRouteOld = route.getFullRoute();
        FullRoute fullRouteNew = new FullRoute();
        int max = fullRouteOld.getBusStopsList().size();
        int j = max - 1;
        while (j > -1) {
            fullRouteNew.addInfo(fullRouteOld.getBusStopsList().get(j), fullRouteOld.getTimeToReachBusStop().get(j));
            j--;
        }
        routeNew.addFullRoute(fullRouteNew);
        return routeNew;
    }

    List<Route> getRoutes(String number) {
        List<Route> answer = new ArrayList<>();
        int i = 0;
        while (i < routesList.size()) {
            Route temp = routesList.get(i);
            if (temp.getNumber().equals(number))
                answer.add(temp);
        }
        return answer;
    }

    Route copyRoute(Route route){
        Route routeNew = new Route(route.getNumber(), "скопированный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        FullRoute fullRouteOld = route.getFullRoute();
        FullRoute fullRouteNew = new FullRoute();
        int max = fullRouteOld.getBusStopsList().size();
        int j = 0;
        while (j < max) {
            fullRouteNew.addInfo(fullRouteOld.getBusStopsList().get(j), fullRouteOld.getTimeToReachBusStop().get(j));
            j++;
        }
        routeNew.addFullRoute(fullRouteNew);
        return routeNew;
    }
}
