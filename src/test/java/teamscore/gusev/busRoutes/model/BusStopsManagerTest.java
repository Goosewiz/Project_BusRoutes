package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusStopsManagerTest {
    @Test
    void createBusStopsManager(){
        BusStopsManager busStopsManager = new BusStopsManager();
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        BusStop busStop = new BusStop(title, latitude, longitude);
        busStopsManager.addBusStop(busStop);
        assertEquals(1, busStopsManager.getBusStopsList().size());
        title = "Метро Московская";
        latitude = 121.45;
        longitude = 124.6;
        BusStop busStop2 = new BusStop(title, latitude, longitude);
        busStopsManager.addBusStop(busStop2);
        title = "Киевская";
        latitude = 120.45;
        longitude = 125.6;
        BusStop busStop3 = new BusStop(title, latitude, longitude);
        busStopsManager.addBusStop(busStop3);
        title = "пл. Памяти";
        latitude = 119.45;
        longitude = 126.6;
        BusStop busStop4 = new BusStop(title, latitude, longitude);
        busStopsManager.addBusStop(busStop4);
        assertEquals(4, busStopsManager.getBusStopsList().size());
        assertEquals(busStop, busStopsManager.getBusStopsList().get(0));
        assertEquals(busStop2, busStopsManager.getBusStopsList().get(1));
        assertEquals(busStop3, busStopsManager.getBusStopsList().get(2));
        assertEquals(busStop4, busStopsManager.getBusStopsList().get(3));
        busStopsManager.addBusStop(busStop);
        assertEquals(4, busStopsManager.getBusStopsList().size());
        busStopsManager.deleteBusStop(busStop2);
        assertEquals(3, busStopsManager.getBusStopsList().size());
        assertEquals(busStop, busStopsManager.getBusStopsList().get(0));
        assertEquals(busStop3, busStopsManager.getBusStopsList().get(1));
        assertEquals(busStop4, busStopsManager.getBusStopsList().get(2));
        busStopsManager.editBusStop(busStop3, busStop2);
        assertEquals(busStop2, busStopsManager.getBusStopsList().get(1));
        LocalTime timeStart = LocalTime.now();
        LocalTime timeEnd = LocalTime.now().plusHours(2);
        Route route = new Route("24", "прямой", 10, timeStart, timeEnd);
        FullRoute fullRoute = new FullRoute();
        fullRoute.addInfo(busStop, timeStart.plusMinutes(10));
        fullRoute.addInfo(busStop2, timeStart.plusMinutes(20));
        fullRoute.addInfo(busStop3, timeStart.plusMinutes(30));
        fullRoute.addInfo(busStop4, timeStart.plusMinutes(40));
        route.addFullRoute(fullRoute);
        RoutesManager routesManager = new RoutesManager();
        routesManager.addRoute(route);
        Route route2 = new Route("34", "прямой", 10, timeStart, timeEnd);
        fullRoute = new FullRoute();
        fullRoute.addInfo(busStop, timeStart.plusMinutes(10));
        fullRoute.addInfo(busStop2, timeStart.plusMinutes(20));
        fullRoute.addInfo(busStop3, timeStart.plusMinutes(30));
        route2.addFullRoute(fullRoute);
        routesManager.addRoute(route2);
        Route route3 = new Route("41", "прямой", 10, timeStart, timeEnd);
        fullRoute = new FullRoute();
        fullRoute.addInfo(busStop, timeStart.plusMinutes(10));
        fullRoute.addInfo(busStop2, timeStart.plusMinutes(20));
        route3.addFullRoute(fullRoute);
        routesManager.addRoute(route3);
        busStopsManager.addBusStop(busStop3);
        LocalTime timeTemp = timeStart.plusMinutes(5);
        Set<Route> answer = busStopsManager.getAllRoutes("Клиники Медуниверситета (ул. Гагарина)");
        assertEquals(3, answer.size());
        LocalTime answerTime = LocalTime.of(23,59,59);
        for (Route routeTemp : answer){
            List<BusStop> tempList = routeTemp.getFullRoute().getBusStopsList();
            int position = tempList.indexOf(busStop);
            LocalTime timeInfo = routeTemp.getFullRoute().getTimeToReachBusStop().get(position);
            timeInfo = timeInfo.minusMinutes(timeTemp.getMinute());
            int value = timeInfo.compareTo(answerTime);
            if (value < 0)
                answerTime = timeInfo;
        }
        assertEquals(5, answerTime.getMinute());
        answer = busStopsManager.getAllRoutes("Метро Московская");
        assertEquals(3, answer.size());
        answer = busStopsManager.getAllRoutes("Киевская");
        assertEquals(2, answer.size());
        answer = busStopsManager.getAllRoutes("пл. Памяти");
        assertEquals(1, answer.size());
    }
}