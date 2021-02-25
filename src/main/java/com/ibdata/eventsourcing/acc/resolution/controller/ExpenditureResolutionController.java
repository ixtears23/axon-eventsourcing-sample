package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionDetailCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDetailVO;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionVO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenditure/resolution")
public class ExpenditureResolutionController {

    private CommandGateway commandGateway;

    public ExpenditureResolutionController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/save")
    public ResponseEntity saveResolution(@RequestBody ResolutionVO resolutionVO) {

        saveExpenditureResolutionCommand(resolutionVO);
        saveExpenditureResolutionDetailCommand(resolutionVO.getResolutionDetailVO());

        return new ResponseEntity(HttpStatus.OK);
    }

    public void saveExpenditureResolutionCommand(ResolutionVO resolutionVO) {

        SaveExpenditureResolutionCommand saveExpenditureResolutionCommand = new SaveExpenditureResolutionCommand(
                resolutionVO.getResolutionId(),
                resolutionVO.getResolutionDate(),
                resolutionVO.getResolutionNumber(),
                resolutionVO.getApplicant(),
                resolutionVO.getApplicationAmount(),
                resolutionVO.getSummary(),
                resolutionVO.getSummary(),
                resolutionVO.getApplicationCategory(),
                resolutionVO.getElectronicPaymentNumber()
        );
        commandGateway.send(saveExpenditureResolutionCommand);
    }

    public void saveExpenditureResolutionDetailCommand(List<ResolutionDetailVO> detailList) {

        for (ResolutionDetailVO detail : detailList) {
            commandGateway.send(new SaveExpenditureResolutionDetailCommand(
                    detail.getResolutionDate(),
                    detail.getResolutionNumber(),
                    detail.getResolutionTurn(),
                    detail.getUser(),
                    detail.getAccNumber(),
                    detail.getCostCode(),
                    detail.getBudgetYear(),
                    detail.getAnnualNumber(),
                    detail.getReceipt(),
                    detail.getExecutionAmount(),
                    detail.getCardNumber(),
                    detail.getApprovalNumber(),
                    detail.getCardCompany(),
                    detail.getBank(),
                    detail.getAccountHolder(),
                    detail.getAccountNumber(),
                    detail.getCustomerName(),
                    detail.getCauseActionNumber()
            ));
        }
    }


}
