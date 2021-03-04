package com.ibdata.eventsourcing.acc.resolution.handler;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.TrackingCommand;
import com.ibdata.eventsourcing.acc.resolution.service.TrackingEventProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrackingCommandHandler {

    @CommandHandler
    public void handle(TrackingCommand command, TrackingEventProcessorService trackingEventProcessorService) {

        log.debug("==============TRACKING_EVENT_PROCESSOR_NAME==============");
        trackingEventProcessorService.replay(command.getTrackingEventProcessorName(), command.getIndex());
        log.debug("=========================================================");
    }
}
