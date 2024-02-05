package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RoutesManager {
    @Getter
    private final List<Route> routesList = new ArrayList<>();

    void addRoute(Route route) {
        if (!routesList.contains(route))
            routesList.add(route);
    }

    void editRoute(Route route, int position) {
        if (routesList.size() > position) {
            routesList.set(position, route);
        }
    }

    void deleteRoute(Route route) {
        routesList.remove(route);
    }

    List<Route> getRoutes(String numberOfRoute) {
        List<Route> answer = new ArrayList<>();
        int i = 0;
        Stream<Route> stream = routesList.stream();
        answer = stream.filter(p -> p.getNumber().equals(numberOfRoute))
                .toList();
        return answer;
    }
}
