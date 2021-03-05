package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class ResolutionDTO {
    String resolutionId;
    String resolutionDate;
    String resolutionNumber;
    String applicant;
    String applicationDepartment;
    String applicationAmount;
    String summary;
    String applicationCategory;
    String electronicPaymentNumber;
    List<ResolutionDetailVO> resolutionDetailVO;
}
