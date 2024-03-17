package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import java.util.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import teamscore.gusev.busRoutes.model.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class BusStopsManagerTest {
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
        SqlScripts.runFromFile(entityManagerFactory, "Insert data.sql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeSession() throws IOException {
        if (entityManager != null) {
            entityManager.close();
        }
        SqlScripts.runFromFile(entityManagerFactory, "ClearAll.sql");
    }

    @Test
    void findBusStop() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search = "Волгина";
        BusStop result = manager.findBusStop(search);
        assertEquals(search, result.getTitle());
    }
    @Test
    void getAllRoutesBetween() {
        BusStopsManager manager = new BusStopsManager(entityManager);
        String search1 = "Волгина";
        String search2 = "Дом молодёжи";
        Route[] answer = manager.getAllRoutesBetween(search1, search2);
        System.out.println(answer[0].getNumber());
        assertEquals(answer[0].getNumber(), "24");
    }
    @Test
    void addBusStop(){
        BusStop busStop = new BusStop("Остановка",12.34,12.35);
        BusStopsManager manager = new BusStopsManager(entityManager);
        manager.addBusStop(busStop);
        BusStop answer = manager.findBusStop("Остановка");
        assertEquals("Остановка", answer.getTitle());
        manager.deleteBusStop(busStop);
    }
}