package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class Route {
    @Getter
    @Setter
    @NonNull
    private String number;
    @Getter
    @Setter
    @NonNull
    private String type;
    @Getter
    @Setter
    @NonNull
    private int interval;
    @Getter
    @Setter
    @NonNull
    private LocalTime timeStart;
    @Getter
    @Setter
    @NonNull
    private LocalTime timeEnd;
}
