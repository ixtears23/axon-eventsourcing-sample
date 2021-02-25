package com.ibdata.eventsourcing.acc.resolution.aggregate;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionDetailCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailChangedEvent;
import com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionDetailCreatedEvent;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class ExpenditureResolutionDetail {

    private String resolutionDate;
    private String resolutionNumber;
    private String resolutionTurn;
    private String user;
    private String accNumber;
    private String costCode;
    private String budgetYear;
    private String annualNumber;
    private String receipt;
    private String executionAmount;
    private String cardNumber;
    private String approvalNumber;
    private String cardCompany;
    private String bank;
    private String accountHolder;
    private String accountNumber;
    private String customerName;
    private String causeActionNumber;

    @CommandHandler
    public void handle(SaveExpenditureResolutionDetailCommand command) {

        if (isNew(command)) {

            apply(new ExpenditureResolutionDetailCreatedEvent(
                    command.getResolutionDate(),
                    command.getResolutionNumber(),
                    command.getResolutionTurn(),
                    command.getUser(),
                    command.getAccNumber(),
                    command.getCostCode(),
                    command.getBudgetYear(),
                    command.getAnnualNumber(),
                    command.getReceipt(),
                    command.getExecutionAmount(),
                    command.getCardNumber(),
                    command.getApprovalNumber(),
                    command.getCardCompany(),
                    command.getBank(),
                    command.getAccountHolder(),
                    command.getAccountNumber(),
                    command.getCustomerName(),
                    command.getCauseActionNumber()
            ));
        } else {
            apply(new ExpenditureResolutionDetailChangedEvent(
                    command.getResolutionDate(),
                    command.getResolutionNumber(),
                    command.getResolutionTurn(),
                    command.getUser(),
                    command.getAccNumber(),
                    command.getCostCode(),
                    command.getBudgetYear(),
                    command.getAnnualNumber(),
                    command.getReceipt(),
                    command.getExecutionAmount(),
                    command.getCardNumber(),
                    command.getApprovalNumber(),
                    command.getCardCompany(),
                    command.getBank(),
                    command.getAccountHolder(),
                    command.getAccountNumber(),
                    command.getCustomerName(),
                    command.getCauseActionNumber()
            ));
        }
    }

    private boolean isNew(SaveExpenditureResolutionDetailCommand command) {

        boolean newResolutionDetail = false;

        if (StringUtils.isBlank(command.getResolutionDate())
                && StringUtils.isBlank(command.getResolutionNumber())
                && StringUtils.isBlank(command.getResolutionTurn())) {
            newResolutionDetail = true;
        }

        return newResolutionDetail;
    }

    @EventSourcingHandler
    public void on(ExpenditureResolutionDetailCreatedEvent event) {
        setExpenditureResolutionDetail(event.getResolutionDate(), event.getResolutionNumber(), event.getResolutionTurn(), event.getUser(), event.getAccNumber(), event.getCostCode(), event.getBudgetYear(), event.getAnnualNumber(), event.getReceipt(), event.getExecutionAmount(), event.getCardNumber(), event.getApprovalNumber(), event.getCardCompany(), event.getBank(), event.getAccountHolder(), event.getAccountNumber(), event.getCustomerName(), event.getCauseActionNumber());
    }

    @EventSourcingHandler
    public void on(ExpenditureResolutionDetailChangedEvent event) {
        setExpenditureResolutionDetail(event.getResolutionDate(), event.getResolutionNumber(), event.getResolutionTurn(), event.getUser(), event.getAccNumber(), event.getCostCode(), event.getBudgetYear(), event.getAnnualNumber(), event.getReceipt(), event.getExecutionAmount(), event.getCardNumber(), event.getApprovalNumber(), event.getCardCompany(), event.getBank(), event.getAccountHolder(), event.getAccountNumber(), event.getCustomerName(), event.getCauseActionNumber());
    }

    private void setExpenditureResolutionDetail(String resolutionDate, String resolutionNumber, String resolutionTurn, String user, String accNumber, String costCode, String budgetYear, String annualNumber, String receipt, String executionAmount, String cardNumber, String approvalNumber, String cardCompany, String bank, String accountHolder, String accountNumber, String customerName, String causeActionNumber) {
        this.resolutionDate = resolutionDate;
        this.resolutionNumber = resolutionNumber;
        this.resolutionTurn = resolutionTurn;
        this.user = user;
        this.accNumber = accNumber;
        this.costCode = costCode;
        this.budgetYear = budgetYear;
        this.annualNumber = annualNumber;
        this.receipt = receipt;
        this.executionAmount = executionAmount;
        this.cardNumber = cardNumber;
        this.approvalNumber = approvalNumber;
        this.cardCompany = cardCompany;
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.causeActionNumber = causeActionNumber;
    }
}
