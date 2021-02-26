package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

import java.util.List;

@Value
public class ResolutionDetailVO {
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
    List<ResolutionAttachmentVO> resolutionAttachmentVO;
}
