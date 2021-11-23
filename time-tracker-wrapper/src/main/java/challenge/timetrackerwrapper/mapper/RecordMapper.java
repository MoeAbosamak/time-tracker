package challenge.timetrackerwrapper.mapper;

import challenge.timetrackerwrapper.data.dto.RecordDTO;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.PostRecord;
import challenge.timetrackerwrapper.data.model.timeTrackerLegacy.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    public abstract RecordDTO recordToRecordDTO(Record record);

    @Named("offsetToLocalDateTime")
    public static String offsetToLocalDateTime(OffsetDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return formatter.format(dateTime);
    }

    @Mapping(source = "start", target = "start", qualifiedByName = "offsetToLocalDateTime")
    @Mapping(source = "end", target = "end", qualifiedByName = "offsetToLocalDateTime")
    public abstract PostRecord recordDTOToPostRecord(RecordDTO recordDTO);


}
