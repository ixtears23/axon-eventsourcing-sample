package com.ibdata.eventsourcing.acc.resolution.aggregate;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ChangeExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.CreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionCreatedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDetailVO;
import com.ibdata.eventsourcing.acc.resolution.domain.ResolutionKeyUtils;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
public class ExpenditureResolutionAggregate {

    @AggregateIdentifier
    private String resolutionId;
    private String resolutionDate;
    private String resolutionNumber;
    private String applicant;
    private String applicationDepartment;
    private String applicationAmount;
    private String summary;
    private String applicationCategory;
    private String electronicPaymentNumber;

    @AggregateMember
    private List<ExpenditureResolutionDetail> detailList = new ArrayList<>();

    private ResolutionQueryMapper queryMapper;

    public ExpenditureResolutionAggregate() {
        // Axon Required...
    }

    @CommandHandler
    public ExpenditureResolutionAggregate(CreateExpenditureResolutionCommand command, ResolutionQueryMapper queryMapper) {
        this.queryMapper = queryMapper;
        applyExpenditureResolutionCreatedEvent(command);
    }

    private void applyExpenditureResolutionCreatedEvent(CreateExpenditureResolutionCommand command) {

        String maxId = queryMapper.findMaxId(ResolutionKeyUtils.getToday());

        List<ResolutionDetailVO> resolutionDetailVOList = new ArrayList<>();
        for (ResolutionDetailVO detailVO : command.getExpenditureResolutionDetailList()) {
            resolutionDetailVOList.add(new ResolutionDetailVO(
                    ResolutionKeyUtils.generateResolutionId(maxId) + detailVO.getResolutionTurn(),
                    ResolutionKeyUtils.getToday(),
                    ResolutionKeyUtils.generateResolutionNumber(maxId),
                    detailVO.getResolutionTurn(),
                    detailVO.getUser(),
                    detailVO.getAccNumber(),
                    detailVO.getCostCode(),
                    detailVO.getBudgetYear(),
                    detailVO.getAnnualNumber(),
                    detailVO.getReceipt(),
                    detailVO.getExecutionAmount(),
                    detailVO.getCardNumber(),
                    detailVO.getApprovalNumber(),
                    detailVO.getCardCompany(),
                    detailVO.getBank(),
                    detailVO.getAccountHolder(),
                    detailVO.getAccountNumber(),
                    detailVO.getCustomerName(),
                    detailVO.getCauseActionNumber(),
                    null
            ));
        }

        apply(new ExpenditureResolutionCreatedEvent(
                ResolutionKeyUtils.generateResolutionId(maxId),
                ResolutionKeyUtils.getToday(),
                ResolutionKeyUtils.generateResolutionNumber(maxId),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber(),
                resolutionDetailVOList
        ));
    }

    @CommandHandler
    private void changeExpenditureResolutionCommand(ChangeExpenditureResolutionCommand command) {
        applyExpenditureResolutionChangedEvent(command);
    }

    private void applyExpenditureResolutionChangedEvent(ChangeExpenditureResolutionCommand command) {
        apply(new ExpenditureResolutionChangedEvent(
                command.getResolutionId(),
                command.getResolutionDate(),
                command.getResolutionNumber(),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber(),
                command.getExpenditureResolutionDetailList()
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
                event.getElectronicPaymentNumber(),
                event.getResolutionDetailVOList());
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
                event.getElectronicPaymentNumber(),
                event.getResolutionDetailVOList());
    }

    private void saveEvent(String resolutionId, String resolutionDate, String resolutionNumber, String applicant, String applicationDepartment, String applicationAmount, String summary, String applicationCategory, String electronicPaymentNumber, List<ResolutionDetailVO> resolutionDetailVO) {
        this.resolutionId = resolutionId;
//        this.resolutionDate = resolutionDate;
//        this.resolutionNumber = resolutionNumber;
//        this.applicant = applicant;
//        this.applicationDepartment = applicationDepartment;
//        this.applicationAmount = applicationAmount;
//        this.summary = summary;
//        this.applicationCategory = applicationCategory;
//        this.electronicPaymentNumber = electronicPaymentNumber;

        for (ResolutionDetailVO detailVO : resolutionDetailVO) {

            ExpenditureResolutionDetailChangedEvent expenditureResolutionDetailCreatedEvent = new ExpenditureResolutionDetailChangedEvent(
                    detailVO.getResolutionDetailId() == null ? resolutionId + detailVO.getResolutionTurn() : detailVO.getResolutionDetailId(),
                    resolutionDate,
                    resolutionNumber,
                    detailVO.getResolutionTurn(),
                    detailVO.getUser(),
                    detailVO.getAccNumber(),
                    detailVO.getCostCode(),
                    detailVO.getBudgetYear(),
                    detailVO.getAnnualNumber(),
                    detailVO.getReceipt(),
                    detailVO.getExecutionAmount(),
                    detailVO.getCardNumber(),
                    detailVO.getApprovalNumber(),
                    detailVO.getCardCompany(),
                    detailVO.getBank(),
                    detailVO.getAccountHolder(),
                    detailVO.getAccountNumber(),
                    detailVO.getCustomerName(),
                    detailVO.getCauseActionNumber()
            );

            ExpenditureResolutionDetail expenditureResolutionDetail = new ExpenditureResolutionDetail(
                    detailVO.getResolutionDetailId() == null ? resolutionId + detailVO.getResolutionTurn() : detailVO.getResolutionDetailId(),
                    resolutionDate,
                    resolutionNumber,
                    detailVO.getResolutionTurn(),
                    detailVO.getUser(),
                    detailVO.getAccNumber(),
                    detailVO.getCostCode(),
                    detailVO.getBudgetYear(),
                    detailVO.getAnnualNumber(),
                    detailVO.getReceipt(),
                    detailVO.getExecutionAmount(),
                    detailVO.getCardNumber(),
                    detailVO.getApprovalNumber(),
                    detailVO.getCardCompany(),
                    detailVO.getBank(),
                    detailVO.getAccountHolder(),
                    detailVO.getAccountNumber(),
                    detailVO.getCustomerName(),
                    detailVO.getCauseActionNumber()
            );

//            this.detailList.add(expenditureResolutionDetail);
            apply(expenditureResolutionDetailCreatedEvent);
        }
    }


}
