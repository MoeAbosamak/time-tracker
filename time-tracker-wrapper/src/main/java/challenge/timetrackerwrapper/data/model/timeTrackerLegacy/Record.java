package challenge.timetrackerwrapper.data.model.timeTrackerLegacy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class Record {
    private String email;
    private OffsetDateTime start;
    private OffsetDateTime end;
}
