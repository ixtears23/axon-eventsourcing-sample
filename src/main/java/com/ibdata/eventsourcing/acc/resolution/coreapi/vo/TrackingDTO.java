package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class TrackingDTO {

    String trackingEventProcessorName;
    long index;
}
