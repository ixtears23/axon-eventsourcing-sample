package com.ibdata.eventsourcing.acc.resolution.coreapi.entity;

import lombok.Value;

/**
 * 결의
 */
@Value
public class TbResolution {
    private String resolutionDate;
    private String resolutionNumber;
    private String applicant;
    private String applicationDepartment;
    private String applicationAmount;
    private String summary;
    private String applicationCategory;
    private String electronicPaymentNumber;
}
