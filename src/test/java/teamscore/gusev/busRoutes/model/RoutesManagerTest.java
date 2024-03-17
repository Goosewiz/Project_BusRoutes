package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoutesManagerTest {
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
    void findRoutes() {
        RoutesManager manager = new RoutesManager(entityManager);
        String search = "24";
        List<Route> result = manager.getRoutes(search);
        assertEquals(4, result.size());
    }

    @Test
    void addRoute() {
        Route route = new Route("99", "прямой", 20, LocalTime.of(8, 0), LocalTime.of(8, 0));
        List<BusAtStop> list = new LinkedList<>();
        RouteWithStops routeWithStops = new RouteWithStops(list, route);
        RoutesManager manager = new RoutesManager(entityManager);
        manager.addRouteWithStops(routeWithStops);
        List<Route> answer = manager.getRoutes("99");
        assertEquals(1, answer.size());
        manager.deleteRouteWithStops(routeWithStops);
    }
}