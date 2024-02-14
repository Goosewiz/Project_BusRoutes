package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class BusStop {
    @Getter
    @Setter
    @NonNull
    private String title;
    @Getter
    @Setter
    @NonNull
    private double latitude;
    @Getter
    @Setter
    @NonNull
    private double longitude;
    @Getter
    private Set<Route> routesSet = new HashSet<>();
    public void addRoute(Route route){
        routesSet.add(route);
    }
    public void removeRoute(Route route){
        routesSet.remove(route);
    }
    public Route[] getAllRoutes(){
        Route[] answer = new Route[routesSet.size()];
        answer = routesSet.toArray(answer);
        return answer;
    }
}
