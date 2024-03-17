package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

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
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @BeforeAll
    public static void setup() throws IOException {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Route.class)
                .addAnnotatedClass(RouteWithStops.class)
                .addAnnotatedClass(BusStop.class)
                .addAnnotatedClass(BusAtStop.class)
                .buildSessionFactory();

        //SqlScripts.runFromFile(entityManagerFactory, "createSchema.sql");
    }

    @AfterAll
    public static void tearDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @BeforeEach
    public void openSession() throws IOException {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeSession() throws IOException {
        if (entityManager != null) {
            entityManager.close();
        }
    }

    @Test
    void copyRoute() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search = "Волгина";
        BusStop result = manager.findBusStop(search);
        List<BusAtStop> list = result.getBusAtStopList();
        RouteWithStops routeWithStops = list.get(0).getRouteWithStops();
        RouteWithStops copy = routeWithStops.copyRouteWithStops();
        assertEquals(routeWithStops.getRoute().getNumber(), copy.getRoute().getNumber());
    }
    @Test
    void reverseRoute() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search = "Волгина";
        BusStop result = manager.findBusStop(search);
        List<BusAtStop> list = result.getBusAtStopList();
        RouteWithStops routeWithStops = list.get(0).getRouteWithStops();
        RouteWithStops copy = routeWithStops.reverseRouteWithStops();
        assertEquals(routeWithStops.getRoute().getNumber(), copy.getRoute().getNumber());
    }
    @Test
    void getTime() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search = "Волгина";
        BusStop result = manager.findBusStop(search);
        List<BusAtStop> list = result.getBusAtStopList();
        RouteWithStops routeWithStops = list.get(0).getRouteWithStops();
        LocalTime localTime = routeWithStops.getTimeByBusStop(result);
        assertEquals(6, localTime.getHour());
    }
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
        BusAtStop.BusAtStopPK pk = new BusAtStop.BusAtStopPK();
        pk.setBusStop(busStop1);
        busAtStop1 = new BusAtStop(pk, 10);
        BusAtStop.BusAtStopPK pk2 = new BusAtStop.BusAtStopPK();
        pk2.setBusStop(busStop2);
        busAtStop2 = new BusAtStop(pk2, 19);
        BusAtStop.BusAtStopPK pk3 = new BusAtStop.BusAtStopPK();
        pk3.setBusStop(busStop3);
        busAtStop3 = new BusAtStop(pk3, 27);
        BusAtStop.BusAtStopPK pk4 = new BusAtStop.BusAtStopPK();
        pk4.setBusStop(busStop4);
        busAtStop4 = new BusAtStop(pk4, 33);
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
        routeWithStops.removeBusAtStop(busAtStop3);
        assertEquals(2, routeWithStops.getBusAtStopList().size());
    }
}