package com.ibdata.eventsourcing.acc.resolution.handler;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.CreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.RequestCreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.query.ExpenditureResolutionQuery;
import com.ibdata.eventsourcing.acc.resolution.domain.ResolutionKeyUtils;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionQueryMapper;
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
    public void handle(RequestCreateExpenditureResolutionCommand command, ResolutionQueryMapper resolutionQueryMapper, CommandGateway commandGateway, QueryGateway queryGateway, EventStore eventStore) throws ExecutionException, InterruptedException {

        String maxId = resolutionQueryMapper.findMaxId(ResolutionKeyUtils.getToday());


        CompletableFuture<ExpenditureResolutionCreatedEvent> query = queryGateway.query(new ExpenditureResolutionQuery("20210302000001"), ExpenditureResolutionCreatedEvent.class);
        ExpenditureResolutionCreatedEvent expenditureResolutionCreatedEvent = query.get();
        log.debug("================queryGateway.query===================");
        log.debug(expenditureResolutionCreatedEvent.toString());
        log.debug("======================================================");

        List<?> collect = eventStore.readEvents(ResolutionKeyUtils.generateResolutionId(maxId), 2).asStream().map(s -> s.getPayload()).collect(Collectors.toList());

        Long aLong = eventStore.lastSequenceNumberFor("20210302000001").get();

        Object o = eventStore.readEvents("20210302000001", aLong).asStream().map(s -> s.getPayload()).collect(Collectors.toList()).get(0);

        log.debug("================eventStore.readEvents===================");
        log.debug(o.toString());
        log.debug("========================================================");


        log.debug("===========eventStore.readEvents.forEach=================");
        collect.forEach(s -> log.debug(s.toString()));
        log.debug("========================================================");


        commandGateway.send(new CreateExpenditureResolutionCommand(
                ResolutionKeyUtils.generateResolutionId(maxId),
                ResolutionKeyUtils.getToday(),
                ResolutionKeyUtils.generateResolutionNumber(maxId),
                command.getApplicant(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber(),
                command.getExpenditureResolutionDetailList()));

    }
}
