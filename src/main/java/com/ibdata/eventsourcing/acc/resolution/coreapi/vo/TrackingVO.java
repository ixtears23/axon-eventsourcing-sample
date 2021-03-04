package com.ibdata.eventsourcing.acc.resolution.coreapi.vo;

import lombok.Value;

@Value
public class TrackingVO {

    String trackingEventProcessorName;
    long index;
}
