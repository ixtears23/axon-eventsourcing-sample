package com.ibdata.eventsourcing.acc.resolution.coreapi.event;

import lombok.Value;
import org.axonframework.serialization.Revision;

@Value
@Revision("2.0")
public class ExpenditureResolutionDetailCreatedEvent {
    String resolutionDetailId;
    String resolutionDate;
    String resolutionNumber;
    String resolutionTurn;
    String user;
    String accNumber;
    String costCode;
    String budgetYear;
    String annualNumber;
    String receipt;
    String executionAmount;
    String cardNumber;
    String approvalNumber;
    String cardCompany;
    String bank;
    String accountHolder;
    String accountNumber;
    String customerName;
    String causeActionNumber;
}
