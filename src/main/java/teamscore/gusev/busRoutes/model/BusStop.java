package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
