package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@RequiredArgsConstructor
public class BusAtStop {
    @Getter
    @Setter
    @NonNull
    private BusStop busStop;
    @Getter
    @Setter
    @NonNull
    private int time;
    public LocalTime getArrivalTime(LocalTime startTime){
        return startTime.plusMinutes(time);
    }
}
