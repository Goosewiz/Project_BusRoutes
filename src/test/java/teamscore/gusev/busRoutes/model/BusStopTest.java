package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusStopTest {
    @Test
    void createBusStops() {
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        BusStop busStop = new BusStop(title, latitude, longitude);
        assertEquals(title, busStop.getTitle());
        assertEquals(latitude, busStop.getLatitude());
        assertEquals(longitude, busStop.getLongitude());
        assertEquals(null, busStop.getRoutesSet());
        Set<Route> routes = new HashSet<>();
        busStop.addRoutesSet(routes);
        assertEquals(0, busStop.getRoutesSet().size());
        LocalTime time = LocalTime.now();
        Route route = new Route("1", "1", 1, time, time);
        routes.add(route);
        assertEquals(1, busStop.getRoutesSet().size());
    }
}