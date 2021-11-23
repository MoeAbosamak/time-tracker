package challenge.timetrackerwrapper.service;

import challenge.timetrackerwrapper.data.dto.RecordDTO;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.PostRecord;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.Record;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.RecordRequest;
import challenge.timetrackerwrapper.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    private final RestTemplate restTemplate;
    private final String QUERY_PARAM_EMAIL_ID="email";
    private final String QUERY_PARAM_OFFSET="offset";
    private final String QUERY_PARAM_LENGTH="length";
    private final String POST_PARAMETER_EMAIL="email";
    private final String POST_PARAMETER_START_DATE_TIME="start";
    private final String POST_PARAMETER_END_DATE_TIME="end";


    @Value("${challenge.timetrackerwrapper.timetrackerlegacy.service.url}")
    private String timeTrackerLegacyUrl;


    public RecordServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RecordDTO> getRecords(RecordRequest recordRequest) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(timeTrackerLegacyUrl)
                .queryParam(QUERY_PARAM_EMAIL_ID, recordRequest.getEmail())
                .queryParam(QUERY_PARAM_LENGTH, recordRequest.getLength())
                .queryParam(QUERY_PARAM_OFFSET, recordRequest.getOffset());

        return Arrays.stream(restTemplate.getForObject(uriBuilder.build().encode().toUri(), Record[].class)).map(RecordMapper.INSTANCE::recordToRecordDTO).collect(Collectors.toList());
    }

    @Override
    public RecordDTO postRecord(RecordDTO recordDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        PostRecord postRecord = RecordMapper.INSTANCE.recordDTOToPostRecord(recordDTO);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(POST_PARAMETER_EMAIL,postRecord.getEmail());
        map.add(POST_PARAMETER_START_DATE_TIME,postRecord.getStart());
        map.add(POST_PARAMETER_END_DATE_TIME,postRecord.getEnd());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        restTemplate.exchange(timeTrackerLegacyUrl, HttpMethod.POST, entity, PostRecord.class);
        return recordDTO;
    }
}
