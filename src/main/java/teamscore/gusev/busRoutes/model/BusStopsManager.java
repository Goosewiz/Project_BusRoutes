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
        BusStop answer = entityManager.createQuery("from BusStop where title ilike :search", BusStop.class)
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
        List<BusAtStop> stops1 = busStop1.getBusAtStopList();
        List<Route> temp = new LinkedList<>();
        for(BusAtStop busAtStop: stops1){
            temp.add(busAtStop.getPk().getRouteWithStops().getRoute());
        }
        List<BusAtStop> stops2 = busStop1.getBusAtStopList();
        List<Route> temp2 = new LinkedList<>();
        for(BusAtStop busAtStop: stops2){
            temp2.add(busAtStop.getPk().getRouteWithStops().getRoute());
        }
        temp.retainAll(temp2);
        Route[] answer = new Route[temp.size()];
        answer = temp.toArray(answer);
        return answer;
    }
}