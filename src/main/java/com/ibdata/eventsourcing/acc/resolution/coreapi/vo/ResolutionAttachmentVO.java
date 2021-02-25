package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class ResolutionAttachmentVO {
    String resolutionDate;
    String resolutionNumber;
    String resolutionTurn;
    String attachmentCategory;
    String attachmentId;
}
