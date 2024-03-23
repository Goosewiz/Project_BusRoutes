package teamscore.gusev.busRoutes.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalTime;

@RequiredArgsConstructor
@NoArgsConstructor
@Embeddable
public class Route {

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
