package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import lombok.Value;

@Value
public class SaveExpenditureResolutionCommand {
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
