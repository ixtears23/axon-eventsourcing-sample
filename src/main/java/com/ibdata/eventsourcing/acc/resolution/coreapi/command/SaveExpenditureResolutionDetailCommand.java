package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import lombok.Value;

@Value
public class SaveExpenditureResolutionDetailCommand {
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
