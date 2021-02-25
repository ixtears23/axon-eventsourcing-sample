package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class SaveExpenditureResolutionCommand {
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
}
