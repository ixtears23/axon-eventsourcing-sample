package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class ReadAndQueryVO {
    String resolutionId;
    long firstSequenceNumber;
}
