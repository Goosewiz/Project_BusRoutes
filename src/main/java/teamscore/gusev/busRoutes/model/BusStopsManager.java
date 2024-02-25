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
        Stream<BusStop> stream = busStopsList.stream();
        Optional busStop = stream.filter(p -> p.getTitle() == title)
                .findFirst();
        BusStop answer = (BusStop) busStop.get();
        if (answer == null)
            throw new RuntimeException("Нет такой остановки");
        return answer;
    }

    Route[] getAllRoutesBetween(String title1, String title2) {
        BusStop busStop1 = findBusStop(title1);
        BusStop busStop2 = findBusStop(title2);
        Route[] answer = getAllRoutesBetween(busStop1, busStop2);
        return answer;
    }

    Route[] getAllRoutesBetween(@NonNull BusStop busStop1, @NonNull BusStop busStop2) {
        Set<Route> temp = busStop1.getRoutesSet();
        temp.retainAll(busStop2.getRoutesSet());
        Route[] answer = new Route[temp.size()];
        answer = temp.toArray(answer);
        return answer;
    }
}