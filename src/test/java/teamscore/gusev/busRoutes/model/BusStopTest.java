package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusStopTest {
    private Route route1;
    private Route route2;
    private Route route3;
    void makeRouteData(){
        String number = "24";
        String type = "прямой";
        int interval = 10;
        LocalTime timeStart = LocalTime.of(8,0);
        LocalTime timeEnd = timeStart.plusHours(2);
        route1 = new Route(number, type, interval, timeStart, timeEnd);
        number = "24";
        type = "обратный";
        interval = 10;
        timeStart = LocalTime.of(8,0);
        timeEnd = timeStart.plusHours(2);
        route2 = new Route(number, type, interval, timeStart, timeEnd);
        number = "41";
        type = "прямой";
        interval = 7;
        timeStart = LocalTime.of(8,0);
        timeEnd = timeStart.plusHours(3);
        route3 = new Route(number, type, interval, timeStart, timeEnd);
    }
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
    @Test
    void getAllRoutes(){
        makeRouteData();
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        BusStop busStop = new BusStop(title, latitude, longitude);
       // busStop.addRoute(route1);
       // busStop.addRoute(route2);
       // busStop.addRoute(route3);
        Route[] answer = busStop.getAllRoutes();
        assertEquals(3, answer.length);
        busStop.removeRoute(route3);
        answer = busStop.getAllRoutes();
        assertEquals(2, answer.length);
    }
}