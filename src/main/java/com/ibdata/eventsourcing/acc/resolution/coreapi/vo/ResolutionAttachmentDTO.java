package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class ResolutionAttachmentDTO {
    String resolutionDate;
    String resolutionNumber;
    String resolutionTurn;
    String attachmentCategory;
    String attachmentId;
}
