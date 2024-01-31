package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FullRouteTest {
    @Test
    void createFullRoute(){
        FullRoute fullRoute = new FullRoute();
        assertEquals(0, fullRoute.getBusStopsList().size());
        assertEquals(0, fullRoute.getTimeToReachBusStop().size());
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        BusStop busStop = new BusStop(title, latitude, longitude);
        LocalTime time = LocalTime.now();
        fullRoute.addInfo(busStop, time);
        assertEquals(1, fullRoute.getBusStopsList().size());
        assertEquals(1, fullRoute.getTimeToReachBusStop().size());
        title = "Метро Московская";
        latitude = 121.45;
        longitude = 124.6;
        BusStop busStop2 = new BusStop(title, latitude, longitude);
        LocalTime time2 = time.plusMinutes(10);
        fullRoute.addInfo(busStop2, time2);
        title = "Киевская";
        latitude = 120.45;
        longitude = 125.6;
        BusStop busStop3 = new BusStop(title, latitude, longitude);
        LocalTime time3 = time2.plusMinutes(10);
        fullRoute.addInfo(busStop3, time3);
        assertEquals(3, fullRoute.getBusStopsList().size());
        assertEquals(3, fullRoute.getTimeToReachBusStop().size());
        title = "пл. Памяти";
        latitude = 119.45;
        longitude = 126.6;
        BusStop busStop4 = new BusStop(title, latitude, longitude);
        LocalTime time4 = time3.plusMinutes(10);
        fullRoute.addInfo(busStop4, time4);
        assertEquals(4, fullRoute.getBusStopsList().size());
        assertEquals(4, fullRoute.getTimeToReachBusStop().size());
        fullRoute.addInfo(busStop, time);
        assertEquals(4, fullRoute.getBusStopsList().size());
        assertEquals(4, fullRoute.getTimeToReachBusStop().size());
        fullRoute.deleteInfo(busStop2);
        assertEquals(3, fullRoute.getBusStopsList().size());
        assertEquals(3, fullRoute.getTimeToReachBusStop().size());
        assertEquals(busStop, fullRoute.getBusStopsList().get(0));
        assertEquals(time, fullRoute.getTimeToReachBusStop().get(0));
        assertEquals(busStop3, fullRoute.getBusStopsList().get(1));
        assertEquals(time3, fullRoute.getTimeToReachBusStop().get(1));
        assertEquals(busStop4, fullRoute.getBusStopsList().get(2));
        assertEquals(time4, fullRoute.getTimeToReachBusStop().get(2));
        fullRoute.editInfo(busStop3, time);
        assertEquals(time, fullRoute.getTimeToReachBusStop().get(1));
    }
}