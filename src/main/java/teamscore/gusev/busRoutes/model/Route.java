package teamscore.gusev.busRoutes.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.*;

import java.time.LocalTime;

@RequiredArgsConstructor
@Entity
@Table(name = "route", schema = "route")
@Embeddable
public class Route {
    @Getter
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "number", nullable = false)
    private String number;

    @Getter
    @Setter
    @NonNull
    @Column(name = "type", nullable = false)
    private String type;

    @Getter
    @Setter
    @NonNull
    @Column(name = "time_interval", nullable = false)
    private int interval;

    @Getter
    @Setter
    @NonNull
    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Getter
    @Setter
    @NonNull
    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;
}
