package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDetailVO;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Value
public class ChangeExpenditureResolutionCommand {
    @TargetAggregateIdentifier
    String resolutionId;
    String resolutionDate;
    String resolutionNumber;
    String applicant;
    String applicationDepartment;
    String applicationAmount;
    String summary;
    String applicationCategory;
    String electronicPaymentNumber;
    List<ResolutionDetailVO> expenditureResolutionDetailList;
}
