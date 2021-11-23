package challenge.timetrackerwrapper.controller;

import challenge.timetrackerwrapper.data.dto.RecordDTO;
import challenge.timetrackerwrapper.service.RecordServiceImpl;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.RecordRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class RecordController {
    private final RecordServiceImpl timeTrackerLegacyImplService;

    public RecordController(RecordServiceImpl timeTrackerLegacyImplService){
        this.timeTrackerLegacyImplService = timeTrackerLegacyImplService;
    }
  
    @GetMapping("/records")
    List<RecordDTO> getRecords(@RequestParam(required = true) String email, @RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer length) {
        RecordRequest recordRequest = RecordRequest.builder().email(email).length(length).offset(offset).build();

        return  timeTrackerLegacyImplService.getRecords(recordRequest);
    }
   
    @PostMapping(value = "/records")
    public RecordDTO postRecord(@RequestBody RecordDTO recordDTO) {
        return timeTrackerLegacyImplService.postRecord(recordDTO);
    }
}
