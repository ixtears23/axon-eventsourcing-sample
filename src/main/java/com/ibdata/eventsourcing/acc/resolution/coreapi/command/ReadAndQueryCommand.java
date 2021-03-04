package com.ibdata.eventsourcing.acc.resolution.coreapi.command;

import lombok.Value;

@Value
public class ReadAndQueryCommand {
    String resolutionId;
    long firstSequenceNumber;
}
