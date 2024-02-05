package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
