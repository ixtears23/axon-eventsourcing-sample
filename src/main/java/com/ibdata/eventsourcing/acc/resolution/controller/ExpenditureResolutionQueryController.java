package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.aggregate.ExpenditureResolutionAggregate;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.query.ExpenditureResolutionQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController("/expenditure/resolution/query")
@Slf4j
public class ExpenditureResolutionQueryController {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public ExpenditureResolutionQueryController(QueryGateway queryGateway, EventStore eventStore) {
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }

    @GetMapping("/all")
    public ExpenditureResolutionCreatedEvent findAll() throws ExecutionException, InterruptedException {
        CompletableFuture<ExpenditureResolutionCreatedEvent> future = queryGateway.query(new ExpenditureResolutionQuery("20210302000001"), ExpenditureResolutionCreatedEvent.class);
        return future.get();
    }

    @GetMapping("/find/{resolutionId}")
    public List<?> findOne(@PathVariable String resolutionId) {

        // SequenceNumber 2 이상인 것 조회
        List<?> list = eventStore.readEvents(resolutionId, 2).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
        return list;
    }
}
