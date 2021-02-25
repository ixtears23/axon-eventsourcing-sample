package com.ibdata.eventsourcing.acc.resolution.projection;

import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolution;
import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolutionDetail;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionMapper;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ExpenditureResolutionProjection {

    private final ResolutionMapper resolutionMapper;

    public ExpenditureResolutionProjection(ResolutionMapper resolutionMapper) {
        this.resolutionMapper = resolutionMapper;
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
}
