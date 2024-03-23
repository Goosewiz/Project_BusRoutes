package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RoutesManager {
    private final EntityManager entityManager;
    void addRouteWithStops(RouteWithStops routeWithStops){
        entityManager.getTransaction().begin();
        entityManager.persist(routeWithStops);
        entityManager.getTransaction().commit();
    }
    void deleteRouteWithStops(RouteWithStops routeWithStops){
        entityManager.getTransaction().begin();
        entityManager.remove(routeWithStops);
        entityManager.getTransaction().commit();
    }


    List<Route> getRoutes(String numberOfRoute) {
        List<Route> answer;
        String number = "%" + numberOfRoute + "%";
        answer = entityManager.createQuery("from RouteWithStops where route.number ilike :number", Route.class)
                .setParameter("number", number)
                .getResultList();
        return answer;
    }
}
