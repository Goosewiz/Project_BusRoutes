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
    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Getter
    @OneToMany(mappedBy = "bus_stop_id", cascade = CascadeType.ALL)
    private Set<BusAtStop> busAtStopSet = new HashSet<>();
   // public void addRoute(Route route){
   //     routesSet.add(route);
   // }
    public void removeRoute(Route route){
        busAtStopSet.remove(route);
    }
    public Route[] getAllRoutes(){
        Route[] answer = new Route[busAtStopSet.size()];
        answer = busAtStopSet.toArray(answer);
        return answer;
    }
}
