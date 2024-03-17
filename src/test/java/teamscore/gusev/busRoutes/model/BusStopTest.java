package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BusStopTest {
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
        Route[] answer = result.getAllRoutes();
        assertEquals(8, answer.length);
    }
}