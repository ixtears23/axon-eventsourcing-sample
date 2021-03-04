package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import lombok.Value;

@Value
public class TrackingCommand {

    String trackingEventProcessorName;
    long index;
}
