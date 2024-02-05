package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusStopsManagerTest {
    private BusStop busStop1;
    private BusStop busStop2;
    private BusStop busStop3;
    private BusStop busStop4;
    private Route route1;
    private Route route2;
    private Route route3;
    private BusAtStop busAtStop1;
    private BusAtStop busAtStop2;
    private BusAtStop busAtStop3;
    private BusAtStop busAtStop4;
    private LinkedList<BusAtStop> list1 = new LinkedList<>();
    private LinkedList<BusAtStop> list2 = new LinkedList<>();
    private LinkedList<BusAtStop> list3 = new LinkedList<>();
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
    void makeBusStopData(){
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        busStop1 = new BusStop(title, latitude, longitude);
        title = "Метро Московская";
        latitude = 121.45;
        longitude = 124.6;
        busStop2 = new BusStop(title, latitude, longitude);
        title = "Киевская";
        latitude = 120.45;
        longitude = 125.6;
        busStop3 = new BusStop(title, latitude, longitude);
        title = "пл. Памяти";
        latitude = 119.45;
        longitude = 126.6;
        busStop4 = new BusStop(title, latitude, longitude);
    }
    void makeBusAtStopData(){
        busAtStop1 = new BusAtStop(busStop1, 10);
        busAtStop2 = new BusAtStop(busStop2, 9);
        busAtStop3 = new BusAtStop(busStop3, 8);
        busAtStop4 = new BusAtStop(busStop4, 7);
        list1.add(busAtStop1);
        list1.add(busAtStop2);
        list2.add(busAtStop1);
        list2.add(busAtStop2);
        list2.add(busAtStop3);
        list3.add(busAtStop1);
        list3.add(busAtStop2);
        list3.add(busAtStop3);
        list3.add(busAtStop4);
    }
    @Test
    void createBusStopsManager(){
        BusStopsManager busStopsManager = new BusStopsManager();
        makeRouteData();
        makeBusStopData();
        makeBusAtStopData();
        busStopsManager.addBusStop(busStop1);
        assertEquals(1, busStopsManager.getBusStopsList().size());
        busStopsManager.addBusStop(busStop2);
        busStopsManager.addBusStop(busStop3);
        busStopsManager.addBusStop(busStop4);
        assertEquals(4, busStopsManager.getBusStopsList().size());
        assertEquals(busStop2, busStopsManager.getBusStopsList().get(1));
        busStopsManager.editBusStop(busStop3, 1);
        assertEquals(busStop3, busStopsManager.getBusStopsList().get(1));
        busStopsManager.deleteBusStop(busStop1);
        assertEquals(3, busStopsManager.getBusStopsList().size());
    }
    @Test
    void getAllRoutes(){
        makeRouteData();
        makeBusStopData();
        makeBusAtStopData();
        BusStopsManager busStopsManager = new BusStopsManager();
        busStopsManager.addBusStop(busStop1);
        busStopsManager.addBusStop(busStop2);
        busStopsManager.addBusStop(busStop3);
        busStopsManager.addBusStop(busStop4);
        RoutesManager routesManager = new RoutesManager();
        routesManager.addRoute(route1);
        routesManager.addRoute(route2);
        routesManager.addRoute(route3);
        FullRoute fullRoute1 = new FullRoute(list1, route1);
        FullRoute fullRoute2 = new FullRoute(list2, route2);
        FullRoute fullRoute3 = new FullRoute(list3, route3);
        FullRouteManager fullRouteManager = new FullRouteManager();
        fullRouteManager.addFullRoute(fullRoute1);
        fullRouteManager.addFullRoute(fullRoute2);
        fullRouteManager.addFullRoute(fullRoute3);
        FullRoute[] answer = busStopsManager.getAllRoutes("Метро Московская");
        assertEquals(3, answer.length);
        answer = busStopsManager.getAllRoutes("пл. Памяти");
        assertEquals(1, answer.length);
    }
    @Test
    void getAllRoutesBetween(){
        makeRouteData();
        makeBusStopData();
        makeBusAtStopData();
        BusStopsManager busStopsManager = new BusStopsManager();
        busStopsManager.addBusStop(busStop1);
        busStopsManager.addBusStop(busStop2);
        busStopsManager.addBusStop(busStop3);
        busStopsManager.addBusStop(busStop4);
        RoutesManager routesManager = new RoutesManager();
        routesManager.addRoute(route1);
        routesManager.addRoute(route2);
        routesManager.addRoute(route3);
        FullRoute fullRoute1 = new FullRoute(list1, route1);
        FullRoute fullRoute2 = new FullRoute(list2, route2);
        FullRoute fullRoute3 = new FullRoute(list3, route3);
        FullRouteManager fullRouteManager = new FullRouteManager();
        fullRouteManager.addFullRoute(fullRoute1);
        fullRouteManager.addFullRoute(fullRoute2);
        fullRouteManager.addFullRoute(fullRoute3);
        FullRoute[] answer = busStopsManager.getAllRoutesBetween("Метро Московская", "пл. Памяти");
        assertEquals(1, answer.length);
        answer = busStopsManager.getAllRoutesBetween("Клиники Медуниверситета (ул. Гагарина)", "Метро Московская");
        assertEquals(3, answer.length);
    }
}