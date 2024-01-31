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

    static void addRoute(Route route) {
        if (!routesList.contains(route))
            routesList.add(route);
    }

    static void editRoute(Route routeOld, Route routeNew) {
        int i = routesList.indexOf(routeOld);
        if (i != -1) {
            routesList.set(i, routeNew);
        }
    }

    static void deleteRoute(Route route) {
        routesList.remove(route);
    }

    static Route reverseRoute(Route route) {
        Route routeNew = new Route(route.getNumber(), "обратный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        if (route.getFullRoute() != null){
            FullRoute fullRouteOld = route.getFullRoute();
            FullRoute fullRouteNew = new FullRoute();
            int max = fullRouteOld.getBusStopsList().size();
            int j = max - 1;
            while (j > -1) {
                fullRouteNew.addInfo(fullRouteOld.getBusStopsList().get(j), fullRouteOld.getTimeToReachBusStop().get(j));
                j--;
            }
            routeNew.addFullRoute(fullRouteNew);
        }
        return routeNew;
    }

    static List<Route> getRoutes(String number) {
        List<Route> answer = new ArrayList<>();
        int i = 0;
        while (i < routesList.size()) {
            Route temp = routesList.get(i);
            if (temp.getNumber().equals(number))
                answer.add(temp);
            i++;
        }
        return answer;
    }

    static Route copyRoute(Route route){
        Route routeNew = new Route(route.getNumber(), "скопированный", route.getInterval(), route.getTimeStart(), route.getTimeEnd());
        if (route.getFullRoute() != null){
            FullRoute fullRouteOld = route.getFullRoute();
            FullRoute fullRouteNew = new FullRoute();
            int max = fullRouteOld.getBusStopsList().size();
            int j = 0;
            while (j < max) {
                fullRouteNew.addInfo(fullRouteOld.getBusStopsList().get(j), fullRouteOld.getTimeToReachBusStop().get(j));
                j++;
            }
            routeNew.addFullRoute(fullRouteNew);
        }
        return routeNew;
    }
}
