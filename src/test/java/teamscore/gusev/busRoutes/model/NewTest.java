package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NewTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @BeforeAll
    public static void setup() throws IOException {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(BusStop.class)
                .buildSessionFactory();

        //SqlScripts.runFromFile(entityManagerFactory, "createSchema.sql");
    }

    @AfterAll
    public static void tearDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

  /*  @BeforeEach
    public void openSession() throws IOException {
        SqlScripts.runFromFile(entityManagerFactory, "insertTestItems.sql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeSession() throws IOException {
        if (entityManager != null) {
            entityManager.close();
        }
        SqlScripts.runFromFile(entityManagerFactory, "clearTestItems.sql");
    }*/

    @ParameterizedTest
    @ValueSource(strings = {"Волгина"})
    void getItemExists(String number) {
        BusStopsManager manager = new BusStopsManager(entityManager);
        BusStop result = manager.findBusStop(number);
        assertEquals(number, result.getTitle());
    }
    //org.hibernate.AnnotationException: Association 'teamscore.gusev.busRoutes.model.BusStop.routesSet' targets the type 'teamscore.gusev.busRoutes.model.Route' which is not an '@Entity' type
}
