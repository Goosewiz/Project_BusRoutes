package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoutesManagerTest {
    private Route route1;
    private Route route2;
    private Route route3;
    void makeData(){
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
    void createRoutesManager(){
        RoutesManager routesManager = new RoutesManager();
        makeData();
        routesManager.addRoute(route1);
        assertEquals(1, routesManager.getRoutesList().size());
        routesManager.addRoute(route2);
        routesManager.addRoute(route3);
        assertEquals(3, routesManager.getRoutesList().size());
        assertEquals(route2, routesManager.getRoutesList().get(1));
        routesManager.editRoute(route3, 1);
        assertEquals(route3, routesManager.getRoutesList().get(1));
        routesManager.deleteRoute(route1);
        assertEquals(2, routesManager.getRoutesList().size());
    }
    @Test
    void getRoutes(){
        RoutesManager routesManager = new RoutesManager();
        makeData();
        routesManager.addRoute(route1);
        routesManager.addRoute(route2);
        routesManager.addRoute(route3);
        List<Route> answer = routesManager.getRoutes("24");
        assertEquals(2, answer.size());
        answer = routesManager.getRoutes("41");
        assertEquals(1, answer.size());
        answer = routesManager.getRoutes("34");
        assertEquals(0, answer.size());
    }
}