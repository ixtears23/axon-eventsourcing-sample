package com.ibdata.eventsourcing.acc.resolution.coreapi.event;

import lombok.Value;

@Value
public class ExpenditureResolutionChangedEvent {
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
