package teamscore.gusev.busRoutes.model;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Stream;
@RequiredArgsConstructor
public class BusStopsManager {
    @Getter
    private final List<BusStop> busStopsList = new ArrayList<>();
    private final EntityManager entityManager;

    void addBusStop(BusStop busStop) {
        entityManager.getTransaction().begin();
        entityManager.persist(busStop);
        entityManager.getTransaction().commit();
    }

    void deleteBusStop(BusStop busStop) {
        entityManager.getTransaction().begin();
        entityManager.remove(busStop);
        entityManager.getTransaction().commit();
    }

    BusStop findBusStop(String title) {
        String search = "%" + title + "%";
        BusStop answer = entityManager.createQuery("from bus_stop.bus_stop where title ilike :search", BusStop.class)
                .setParameter("search", search)
                .getSingleResult();
        return answer;
    }

    Route[] getAllRoutesBetween(String title1, String title2) {
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        Route[] answer = getAllRoutesBetween(busStop1, busStop2);
        return answer;
    }

    Route[] getAllRoutesBetween(@NonNull BusStop busStop1, @NonNull BusStop busStop2) {
        Set<BusAtStop> tmp = busStop1.getBusAtStopSet();
        Set<Route> temp = new HashSet<>();
        for(BusAtStop busAtStop: tmp){
            temp.add(busAtStop.getRoute());
        }
        temp.retainAll(busStop2.getBusAtStopSet());
        Route[] answer = new Route[temp.size()];
        answer = temp.toArray(answer);
        return answer;
    }
}