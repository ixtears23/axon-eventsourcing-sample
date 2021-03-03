package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExpenditureResolutionCommandHandler {

    private final ResolutionMapper resolutionMapper;

    public ExpenditureResolutionCommandHandler(ResolutionMapper resolutionMapper) {
        this.resolutionMapper = resolutionMapper;
    }

    @CommandHandler
    public void handle() {

    }


}
