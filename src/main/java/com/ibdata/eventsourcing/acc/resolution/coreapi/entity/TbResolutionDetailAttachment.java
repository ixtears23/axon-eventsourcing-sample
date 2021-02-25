package com.ibdata.eventsourcing.acc.resolution.coreapi.entity;

import lombok.Value;

/**
 * 결의상세첨부
 */
@Value
public class TbResolutionDetailAttachment {
    private String resolutionDate;
    private String resolutionNumber;
    private String resolutionTurn;
    private String attachmentCategory;
}
