package com.ibdata.eventsourcing.acc.resolution.aggregate;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.*;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.domain.ResolutionKeyUtils;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionQueryMapper;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ExpenditureResolutionAggregate {

    @AggregateIdentifier
    private String ResolutionId;
    private String resolutionDate;
    private String resolutionNumber;
    private String applicant;
    private String applicationDepartment;
    private String applicationAmount;
    private String summary;
    private String applicationCategory;
    private String electronicPaymentNumber;

//    @AggregateMember
    private List<ExpenditureResolutionDetail> detailList;

    @Autowired
    private ResolutionQueryMapper queryMapper;

    @CommandHandler
    public void handle(SaveExpenditureResolutionCommand command) {
        if (StringUtils.isNotBlank(command.getResolutionId())) {
            applyExpenditureResolutionCreatedEvent(command);
        } else {
            applyExpenditureResolutionChangedEvent(command);
        }
    }

    private void applyExpenditureResolutionCreatedEvent(SaveExpenditureResolutionCommand command) {

        String maxId = queryMapper.findMaxId(ResolutionKeyUtils.getToday());

        apply(new ExpenditureResolutionCreatedEvent(
                ResolutionKeyUtils.generateResolutionId(maxId),
                ResolutionKeyUtils.getToday(),
                ResolutionKeyUtils.generateResolutionNumber(maxId),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber()
        ));
    }

    private void applyExpenditureResolutionChangedEvent(SaveExpenditureResolutionCommand command) {
        apply(new ExpenditureResolutionChangedEvent(
                command.getResolutionId(),
                command.getResolutionDate(),
                command.getResolutionNumber(),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber()
        ));
    }

    @EventSourcingHandler
    public void on(ExpenditureResolutionCreatedEvent event) {
        saveEvent(event.getResolutionId(),
                event.getResolutionDate(),
                event.getResolutionNumber(),
                event.getApplicant(),
                event.getApplicationDepartment(),
                event.getApplicationAmount(),
                event.getSummary(),
                event.getApplicationCategory(),
                event.getElectronicPaymentNumber());
    }

    @EventSourcingHandler
    public void on(ExpenditureResolutionChangedEvent event) {
        saveEvent(event.getResolutionId(),
                event.getResolutionDate(),
                event.getResolutionNumber(),
                event.getApplicant(),
                event.getApplicationDepartment(),
                event.getApplicationAmount(),
                event.getSummary(),
                event.getApplicationCategory(),
                event.getElectronicPaymentNumber());
    }

    private void saveEvent(String resolutionId, String resolutionDate, String resolutionNumber, String applicant, String applicationDepartment, String applicationAmount, String summary, String applicationCategory, String electronicPaymentNumber) {
        this.ResolutionId = resolutionId;
        this.resolutionDate = resolutionDate;
        this.resolutionNumber = resolutionNumber;
        this.applicant = applicant;
        this.applicationDepartment = applicationDepartment;
        this.applicationAmount = applicationAmount;
        this.summary = summary;
        this.applicationCategory = applicationCategory;
        this.electronicPaymentNumber = electronicPaymentNumber;
    }


}
