package challenge.timetrackerwrapper.mapper;

import challenge.timetrackerwrapper.data.dto.RecordDTO;
import challenge.timetrackerwrapper.data.dto.RecordDTO.RecordDTOBuilder;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.PostRecord;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.PostRecord.PostRecordBuilder;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.Record;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-23T05:10:37+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.6 (Oracle Corporation)"
)
public class RecordMapperImpl implements RecordMapper {

    @Override
    public RecordDTO recordToRecordDTO(Record record) {
        if ( record == null ) {
            return null;
        }

        RecordDTOBuilder recordDTO = RecordDTO.builder();

        recordDTO.email( record.getEmail() );
        recordDTO.start( record.getStart() );
        recordDTO.end( record.getEnd() );

        return recordDTO.build();
    }

    @Override
    public PostRecord recordDTOToPostRecord(RecordDTO recordDTO) {
        if ( recordDTO == null ) {
            return null;
        }

        PostRecordBuilder postRecord = PostRecord.builder();

        postRecord.start( RecordMapper.offsetToLocalDateTime( recordDTO.getStart() ) );
        postRecord.end( RecordMapper.offsetToLocalDateTime( recordDTO.getEnd() ) );
        postRecord.email( recordDTO.getEmail() );

        return postRecord.build();
    }
}
