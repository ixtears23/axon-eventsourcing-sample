package com.ibdata.eventsourcing.acc.resolution.coreapi.entity;

import lombok.Value;

/**
 * 결의상세
 */
@Value
public class TbResolutionDetail {
    private String resolutionDate;
    private String resolutionNumber;
    private String resolutionTurn;
    private String user;
    private String accNumber;
    private String costCode;
    private String budgetYear;
    private String annualNumber;
    private String receipt;
    private String executionAmount;
    private String cardNumber;
    private String approvalNumber;
    private String cardCompany;
    private String bank;
    private String accountHolder;
    private String accountNumber;
    private String customerName;
    private String causeActionNumber;
}
