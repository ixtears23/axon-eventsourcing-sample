package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class ReadAndQueryDTO {
    String resolutionId;
    long firstSequenceNumber;
}
