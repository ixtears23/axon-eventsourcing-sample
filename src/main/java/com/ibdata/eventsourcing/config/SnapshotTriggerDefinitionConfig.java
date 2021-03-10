package com.ibdata.eventsourcing.config;

import com.ibdata.eventsourcing.acc.resolution.aggregate.ExpenditureResolutionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
@Slf4j
public class SnapshotTriggerDefinitionConfig {

    @Bean
    public AggregateFactory<ExpenditureResolutionAggregate> aggregateFactory() {
        log.debug("==========SnapshotTriggerDefinitionConfig.aggregateFactory==========");
        return new GenericAggregateFactory<>(ExpenditureResolutionAggregate.class);
    }

    @Bean
    public Snapshotter snapshotter(EventStore eventStore, TransactionManager transactionManager) {
        log.debug("==========SnapshotTriggerDefinitionConfig.snapshotter==========");
        return AggregateSnapshotter
                .builder()
                .eventStore(eventStore)
                .aggregateFactories(aggregateFactory())
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        log.debug("==========SnapshotTriggerDefinitionConfig.snapshotTriggerDefinition==========");
        final int THRESHOLD = 5;
        return new EventCountSnapshotTriggerDefinition(snapshotter, THRESHOLD);
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }

    @Bean
    public Repository<ExpenditureResolutionAggregate> expenditureResolutionAggregateRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition) {
        log.debug("==========SnapshotTriggerDefinitionConfig.expenditureResolutionAggregateRepository==========");
        return EventSourcingRepository
                .builder(ExpenditureResolutionAggregate.class)
                .eventStore(eventStore)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .build();
    }
}
