package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoutesManagerTest {
    @Test
    void createRoutesManager(){
        RoutesManager routesManager = new RoutesManager();
        String number = "24";
        String type = "прямой";
        int interval = 10;
        LocalTime timeStart = LocalTime.now();
        LocalTime timeEnd = timeStart.plusHours(2);
        Route route = new Route(number, type, interval, timeStart, timeEnd);
        routesManager.addRoute(route);
        assertEquals(1, routesManager.getRoutesList().size());
        Route route2 = routesManager.copyRoute(route);
        assertEquals(route.getNumber(), route2.getNumber());
        assertEquals("скопированный", route2.getType());
        assertEquals(route.getInterval(), route2.getInterval());
        assertEquals(route.getTimeStart(), route2.getTimeStart());
        assertEquals(route.getTimeEnd(), route2.getTimeEnd());
        routesManager.addRoute(route2);
        Route route3 = routesManager.reverseRoute(route);
        assertEquals(route.getNumber(), route3.getNumber());
        assertEquals("обратный", route3.getType());
        assertEquals(route.getInterval(), route3.getInterval());
        assertEquals(route.getTimeStart(), route3.getTimeStart());
        assertEquals(route.getTimeEnd(), route3.getTimeEnd());
        routesManager.addRoute(route3);
        assertEquals(3, routesManager.getRoutesList().size());
        routesManager.addRoute(route);
        assertEquals(3, routesManager.getRoutesList().size());
        routesManager.deleteRoute(route2);
        assertEquals(2, routesManager.getRoutesList().size());
        routesManager.editRoute(route3, route2);
        assertEquals(route2.getNumber(), routesManager.getRoutesList().get(1).getNumber());
        assertEquals(route2.getType(), routesManager.getRoutesList().get(1).getType());
        assertEquals(route2.getInterval(), routesManager.getRoutesList().get(1).getInterval());
        assertEquals(route2.getTimeStart(), routesManager.getRoutesList().get(1).getTimeStart());
        assertEquals(route2.getTimeEnd(), routesManager.getRoutesList().get(1).getTimeEnd());
        List<Route> answer = routesManager.getRoutes("24");
        assertEquals(2, answer.size());
        assertEquals("прямой", answer.get(0).getType());
        assertEquals("скопированный", answer.get(1).getType());
    }
}