package teamscore.gusev.busRoutes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BusAtStopTest {
    private BusStop busStop1;
    void makeData(){
        String title = "Клиники Медуниверситета (ул. Гагарина)";
        double latitude = 122.45;
        double longitude = 123.6;
        busStop1 = new BusStop(title, latitude, longitude);
    }
    @Test
    void busAtStop(){
        makeData();
        BusAtStop busAtStop1 = new BusAtStop(busStop1, 9);
        assertEquals(busStop1, busAtStop1.getBusStop());
        assertEquals(9, busAtStop1.getTime());
        LocalTime localTime = LocalTime.of(2,0);
        localTime = busAtStop1.getArrivalTime(localTime);
        assertEquals(9, localTime.getMinute());
    }
}