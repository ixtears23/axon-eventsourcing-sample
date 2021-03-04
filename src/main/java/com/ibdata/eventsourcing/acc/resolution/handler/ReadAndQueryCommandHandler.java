package com.ibdata.eventsourcing.acc.resolution.handler;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ReadAndQueryCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.query.ExpenditureResolutionQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReadAndQueryCommandHandler {

    @CommandHandler
    public void handle(ReadAndQueryCommand command, QueryGateway queryGateway, EventStore eventStore) throws ExecutionException, InterruptedException {

        CompletableFuture<ExpenditureResolutionCreatedEvent> query = queryGateway.query(new ExpenditureResolutionQuery(command.getResolutionId()), ExpenditureResolutionCreatedEvent.class);
        ExpenditureResolutionCreatedEvent expenditureResolutionCreatedEvent = query.get();
        log.debug("================queryGateway.query===================");
        log.debug(expenditureResolutionCreatedEvent.toString());
        log.debug("======================================================");

        List<?> collect = eventStore.readEvents(command.getResolutionId(), 2).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
        Long aLong = eventStore.lastSequenceNumberFor(command.getResolutionId()).get();
        Object o = eventStore.readEvents(command.getResolutionId(), aLong).asStream().map(s -> s.getPayload()).collect(Collectors.toList()).get(0);

        log.debug("================eventStore.readEvents===================");
        log.debug(o.toString());
        log.debug("========================================================");


        log.debug("===========eventStore.readEvents.forEach=================");
        collect.forEach(s -> log.debug(s.toString()));
        log.debug("========================================================");
    }
}
