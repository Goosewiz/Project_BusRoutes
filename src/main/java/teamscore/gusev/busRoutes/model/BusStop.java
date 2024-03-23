package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Table(name = "bus_stop", schema = "bus_stop")
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Getter
    @OneToMany(mappedBy = "pk.busStop", cascade = CascadeType.ALL)
    private List<BusAtStop> busAtStopList = new ArrayList<>();
    public void addRoute(BusAtStop busAtStop){
        busAtStopList.add(busAtStop);
    }
    public void removeRoute(Route route){
        busAtStopList.remove(route);
    }
    public Route[] getAllRoutes(){
        List<Route> list = new ArrayList<>();
        for (BusAtStop busAtStop: busAtStopList){
            RouteWithStops temp = busAtStop.getRouteWithStops();
            Route route = temp.getRoute();
            list.add(route);
        }
        Route[] answer = new Route[list.size()];
        answer = list.toArray(answer);
        return answer;
    }
}
