package challenge.timetrackerwrapper.service;

import challenge.timetrackerwrapper.data.dto.RecordDTO;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.RecordRequest;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getRecords(RecordRequest recordRequest);
    RecordDTO postRecord(RecordDTO recordDTO);

}
