package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RoutesManager {
    @Getter
    private final List<RouteWithStops> routeWithStopsList = new ArrayList<>();
    void addRouteWithStops(RouteWithStops routeWithStops){
        if (!routeWithStopsList.contains(routeWithStops))
            routeWithStopsList.add(routeWithStops);
    }
    void deleteRouteWithStops(RouteWithStops routeWithStops){
        routeWithStopsList.remove(routeWithStops);
    }


    List<RouteWithStops> getRoutes(String numberOfRoute) {
        List<RouteWithStops> answer;
        Stream<RouteWithStops> stream = routeWithStopsList.stream();
        answer = stream.filter(p -> p.getRoute().getNumber().equals(numberOfRoute))
                .toList();
        return answer;
    }
}
