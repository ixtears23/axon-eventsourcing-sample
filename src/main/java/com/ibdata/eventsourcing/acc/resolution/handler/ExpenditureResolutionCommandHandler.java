package com.ibdata.eventsourcing.acc.resolution.handler;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.CreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ReadAndQueryCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.RequestCreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.query.ExpenditureResolutionQuery;
import com.ibdata.eventsourcing.acc.resolution.domain.ResolutionKeyUtils;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionQueryMapper;
import com.ibdata.eventsourcing.acc.resolution.service.TrackingEventProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExpenditureResolutionCommandHandler {


    @CommandHandler
    public void handle(RequestCreateExpenditureResolutionCommand command, ResolutionQueryMapper resolutionQueryMapper, CommandGateway commandGateway) throws ExecutionException, InterruptedException {

//        String maxId = resolutionQueryMapper.findMaxId(ResolutionKeyUtils.getToday());
        String maxId = "7";

        commandGateway.send(new CreateExpenditureResolutionCommand(
                ResolutionKeyUtils.generateResolutionId(maxId),
                ResolutionKeyUtils.getToday(),
                ResolutionKeyUtils.generateResolutionNumber(maxId),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber(),
                command.getExpenditureResolutionDetailList()));

    }
}
