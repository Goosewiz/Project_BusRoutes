package teamscore.gusev.busRoutes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@RequiredArgsConstructor
@Entity
@Table(name = "bus_at_stop", schema = "bus_stop")
public class BusAtStop {
    @Getter
    @Setter
    @EmbeddedId
    private long bus_stop_id;

    @Getter
    @Setter
    @EmbeddedId
    private long route_id;

    @Getter
    @Setter
    @NonNull
    @OneToOne(mappedBy = "bus_stop.bus_stop", cascade = CascadeType.ALL)
    private BusStop busStop;

    @Getter
    @Setter
    @NonNull
    @Column(name = "time", nullable = false)
    private int time;
    public LocalTime getArrivalTime(LocalTime startTime){
        return startTime.plusMinutes(time);
    }
}
