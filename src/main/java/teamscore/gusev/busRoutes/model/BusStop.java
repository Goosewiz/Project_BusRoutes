package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Table(name = "bus_stop", schema = "bus_stop")
@Embeddable
public class BusStop {
    @Getter
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @Getter
    @Setter
    @NonNull
    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Getter
    @Setter
    @NonNull
    @Column(name = "longitude")
    private double longitude;

    @Getter
    @OneToMany(mappedBy = "route.route", cascade = CascadeType.ALL)
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
