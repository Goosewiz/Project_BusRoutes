package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {
    @Test
    void createRoute(){
        String number = "24";
        String type = "прямой";
        int interval = 10;
        LocalTime timeStart = LocalTime.of(8,0);
        LocalTime timeEnd = timeStart.plusHours(2);
        Route route = new Route(number, type, interval, timeStart, timeEnd);
        assertEquals(number, route.getNumber());
        assertEquals(type, route.getType());
        assertEquals(interval, route.getInterval());
        assertEquals(timeStart, route.getTimeStart());
        assertEquals(timeEnd, route.getTimeEnd());
    }
}