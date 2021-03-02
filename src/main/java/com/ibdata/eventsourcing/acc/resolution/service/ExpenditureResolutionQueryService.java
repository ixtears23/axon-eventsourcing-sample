package com.ibdata.eventsourcing.acc.resolution.service;

import com.ibdata.eventsourcing.acc.resolution.aggregate.ExpenditureResolutionAggregate;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.query.ExpenditureResolutionQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenditureResolutionQueryService {

    private final EventStore eventStore;

    public ExpenditureResolutionQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @QueryHandler
    public ExpenditureResolutionCreatedEvent getExpenditureResolution(ExpenditureResolutionQuery query) {

        log.debug("=================QueryServiceStart=================");
        log.debug(query.getResolutionId());
        log.debug("=================QueryServiceEnd=================");

        List<?> collect = eventStore.readEvents(query.getResolutionId()).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
        return (ExpenditureResolutionCreatedEvent) collect.get(0);
    }
}
