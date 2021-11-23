package challenge.timetrackerwrapper.data.model.timeTrackerLegacy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class RecordRequest {
    private String email;
    private Integer offset;
    private Integer length;
}
