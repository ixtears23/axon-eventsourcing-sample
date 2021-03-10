package com.ibdata.eventsourcing.acc.resolution.projection;

import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolution;
import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolutionDetail;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionMapper;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("com.ibdata.eventsourcing.acc.resolution.projection")
public class ExpenditureResolutionProjection {

    private final ResolutionMapper resolutionMapper;

    public ExpenditureResolutionProjection(ResolutionMapper resolutionMapper) {
        this.resolutionMapper = resolutionMapper;
    }


    @EventHandler
    public void on(ExpenditureResolutionCreatedEvent resolutionCreatedEvent) {
        resolutionMapper.insertResolution(new TbResolution(resolutionCreatedEvent.getResolutionDate(),
                resolutionCreatedEvent.getResolutionNumber(),
                resolutionCreatedEvent.getApplicant(),
                resolutionCreatedEvent.getApplicationDepartment(),
                resolutionCreatedEvent.getApplicationAmount(),
                resolutionCreatedEvent.getSummary(),
                resolutionCreatedEvent.getApplicationCategory(),
                resolutionCreatedEvent.getElectronicPaymentNumber()));
    }

    @EventHandler
    public void on(ExpenditureResolutionDetailCreatedEvent resolutionDetailCreatedEvent) {
        TbResolutionDetail tbResolutionDetail = new TbResolutionDetail(resolutionDetailCreatedEvent.getResolutionDate(),
                resolutionDetailCreatedEvent.getResolutionNumber(),
                resolutionDetailCreatedEvent.getResolutionTurn(),
                resolutionDetailCreatedEvent.getUser(),
                resolutionDetailCreatedEvent.getAccNumber(),
                resolutionDetailCreatedEvent.getCostCode(),
                resolutionDetailCreatedEvent.getBudgetYear(),
                resolutionDetailCreatedEvent.getAnnualNumber(),
                resolutionDetailCreatedEvent.getReceipt(),
                resolutionDetailCreatedEvent.getExecutionAmount(),
                resolutionDetailCreatedEvent.getCardNumber(),
                resolutionDetailCreatedEvent.getApprovalNumber(),
                resolutionDetailCreatedEvent.getCardCompany(),
                resolutionDetailCreatedEvent.getBank(),
                resolutionDetailCreatedEvent.getAccountHolder(),
                resolutionDetailCreatedEvent.getAccountNumber(),
                resolutionDetailCreatedEvent.getCustomerName(),
                resolutionDetailCreatedEvent.getCauseActionNumber());

        resolutionMapper.insertResolutionDetail(tbResolutionDetail);
    }

    @EventHandler
    public void on(ExpenditureResolutionChangedEvent expenditureResolutionChangedEvent) {
        resolutionMapper.updateResolution(new TbResolution(expenditureResolutionChangedEvent.getResolutionDate(),
                expenditureResolutionChangedEvent.getResolutionNumber(),
                expenditureResolutionChangedEvent.getApplicant(),
                expenditureResolutionChangedEvent.getApplicationDepartment(),
                expenditureResolutionChangedEvent.getApplicationAmount(),
                expenditureResolutionChangedEvent.getSummary(),
                expenditureResolutionChangedEvent.getApplicationCategory(),
                expenditureResolutionChangedEvent.getElectronicPaymentNumber()));
    }

    @EventHandler
    public void on(ExpenditureResolutionDetailChangedEvent expenditureResolutionDetailChangedEvent) {
        TbResolutionDetail tbResolutionDetail = new TbResolutionDetail(expenditureResolutionDetailChangedEvent.getResolutionDate(),
                expenditureResolutionDetailChangedEvent.getResolutionNumber(),
                expenditureResolutionDetailChangedEvent.getResolutionTurn(),
                expenditureResolutionDetailChangedEvent.getUser(),
                expenditureResolutionDetailChangedEvent.getAccNumber(),
                expenditureResolutionDetailChangedEvent.getCostCode(),
                expenditureResolutionDetailChangedEvent.getBudgetYear(),
                expenditureResolutionDetailChangedEvent.getAnnualNumber(),
                expenditureResolutionDetailChangedEvent.getReceipt(),
                expenditureResolutionDetailChangedEvent.getExecutionAmount(),
                expenditureResolutionDetailChangedEvent.getCardNumber(),
                expenditureResolutionDetailChangedEvent.getApprovalNumber(),
                expenditureResolutionDetailChangedEvent.getCardCompany(),
                expenditureResolutionDetailChangedEvent.getBank(),
                expenditureResolutionDetailChangedEvent.getAccountHolder(),
                expenditureResolutionDetailChangedEvent.getAccountNumber(),
                expenditureResolutionDetailChangedEvent.getCustomerName(),
                expenditureResolutionDetailChangedEvent.getCauseActionNumber());

        resolutionMapper.updateResolutionDetail(tbResolutionDetail);
    }

}
