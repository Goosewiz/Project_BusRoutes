package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus_at_stop", schema = "bus_stop")
public class BusAtStop {
    @Embeddable
    static class BusAtStopPK{
        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "bus_stop_id")
        private BusStop busStop;
        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "route_id")
        private RouteWithStops routeWithStops;
    }
    @EmbeddedId
    @Getter
    @Setter
    @NonNull
    private BusAtStopPK pk = new BusAtStopPK();

    @Transient
    public BusStop getBusStop(){
        return pk.getBusStop();
    }

    @Transient
    public RouteWithStops getRouteWithStops(){
        return pk.getRouteWithStops();
    }

    @Getter
    @Setter
    @NonNull
    @Column(name = "time", nullable = false)
    private int time;
    public LocalTime getArrivalTime(LocalTime startTime){
        return startTime.plusMinutes(time);
    }
}
