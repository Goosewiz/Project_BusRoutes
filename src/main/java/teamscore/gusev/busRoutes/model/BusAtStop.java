package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Table(name = "bus_at_stop", schema = "bus_stop")
public class BusAtStop {
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "bus_stop_id")
    private BusStop busStop;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Getter
    @Setter
    @NonNull
    @Column(name = "time", nullable = false)
    private int time;
    public LocalTime getArrivalTime(LocalTime startTime){
        return startTime.plusMinutes(time);
    }
}
