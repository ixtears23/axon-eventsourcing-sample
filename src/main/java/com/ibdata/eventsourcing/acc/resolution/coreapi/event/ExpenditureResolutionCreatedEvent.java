package com.ibdata.eventsourcing.acc.resolution.coreapi.event;

import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDetailVO;
import lombok.Value;
import org.axonframework.serialization.Revision;

import java.util.List;

@Value
@Revision("2.0")
public class ExpenditureResolutionCreatedEvent {
    String resolutionId;
    String resolutionDate;
    String resolutionNumber;
    String applicant;
    String applicationDepartment;
    String applicationAmount;
    String summary;
    String applicationCategory;
    String electronicPaymentNumber;
    List<ResolutionDetailVO> resolutionDetailVOList;
}
