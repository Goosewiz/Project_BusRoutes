package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteWithStopsTest {
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
        busAtStop2 = new BusAtStop(busStop2, 19);
        busAtStop3 = new BusAtStop(busStop3, 27);
        busAtStop4 = new BusAtStop(busStop4, 33);
        list1.add(busAtStop1);
        list1.add(busAtStop2);
    }
    @Test
    void createRouteWithStops(){
        makeBusStopData();
        makeRouteData();
        makeBusAtStopData();
        RouteWithStops routeWithStops = new RouteWithStops(list1, route1);
        assertEquals(list1, routeWithStops.getBusAtStopList());
        assertEquals(route1, routeWithStops.getRoute());
        assertEquals(2, routeWithStops.getBusAtStopList().size());
        routeWithStops.addBusAtStop(busAtStop3);
        assertEquals(3, routeWithStops.getBusAtStopList().size());
        assertEquals(busAtStop3, routeWithStops.getBusAtStopList().get(2));
        routeWithStops.deleteBusAtStop(busAtStop3);
        assertEquals(2, routeWithStops.getBusAtStopList().size());
    }
    @Test
    void reverseFullRoute(){
        makeBusStopData();
        makeRouteData();
        makeBusAtStopData();
        RouteWithStops routeWithStops = new RouteWithStops(list1, route1);
        RouteWithStops routeWithStops2 = routeWithStops.reverseRouteWithStops();
        assertEquals(routeWithStops.getRoute().getNumber(),routeWithStops2.getRoute().getNumber());
        assertEquals("обратный",routeWithStops2.getRoute().getType());
        assertEquals(busAtStop2, routeWithStops2.getBusAtStopList().get(0));
    }
    @Test
    void copyFullRoute(){
        makeBusStopData();
        makeRouteData();
        makeBusAtStopData();
        RouteWithStops routeWithStops = new RouteWithStops(list1, route1);
        RouteWithStops routeWithStops2 = routeWithStops.copyRouteWithStops();
        assertEquals(routeWithStops.getRoute().getNumber(),routeWithStops2.getRoute().getNumber());
        assertEquals("скопированный",routeWithStops2.getRoute().getType());
        assertEquals(busAtStop1, routeWithStops2.getBusAtStopList().get(0));
    }
    @Test
    void getTimeByBusStop() {
        makeBusStopData();
        makeRouteData();
        makeBusAtStopData();
        RouteWithStops routeWithStops = new RouteWithStops(list1, route1);
        LocalTime answer = routeWithStops.getTimeByBusStop(busStop1);
        assertEquals(10, answer.getMinute());
    }
}