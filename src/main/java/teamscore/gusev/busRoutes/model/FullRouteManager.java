package teamscore.gusev.busRoutes.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FullRouteManager {
    @Getter
    private static final List<FullRoute> fullRouteList = new ArrayList<>();
    void addFullRoute(FullRoute fullRoute){
        if (!fullRouteList.contains(fullRoute))
            fullRouteList.add(fullRoute);
    }
    void deleteFullRoute(FullRoute fullRoute){
        fullRouteList.remove(fullRoute);
    }
}
