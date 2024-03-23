package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusAtStopTest {
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
    void findBusStop() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search = "Волгина";
        BusStop result = manager.findBusStop(search);
        List<BusAtStop> busAtStop = result.getBusAtStopList();
        LocalTime localTime = LocalTime.of(8,0,0);
        LocalTime time = busAtStop.get(0).getArrivalTime(localTime);
        LocalTime expected = LocalTime.of(8,40,0);
        assertEquals(expected,time);
    }
}