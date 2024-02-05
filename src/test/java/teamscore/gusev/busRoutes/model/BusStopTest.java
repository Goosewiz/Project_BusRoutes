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
    }
}