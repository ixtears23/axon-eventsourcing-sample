package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ChangeExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.CreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenditure/resolution")
@Slf4j
public class ExpenditureResolutionController {

    private CommandGateway commandGateway;

    public ExpenditureResolutionController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/save")
    public ResponseEntity saveResolution(@RequestBody ResolutionVO resolutionVO) {

        if (StringUtils.isBlank(resolutionVO.getResolutionId())) {
            CreateExpenditureResolutionCommand saveExpenditureResolutionCommand = new CreateExpenditureResolutionCommand(
                    resolutionVO.getResolutionId(),
                    resolutionVO.getResolutionDate(),
                    resolutionVO.getResolutionNumber(),
                    resolutionVO.getApplicant(),
                    resolutionVO.getApplicationAmount(),
                    resolutionVO.getSummary(),
                    resolutionVO.getSummary(),
                    resolutionVO.getApplicationCategory(),
                    resolutionVO.getElectronicPaymentNumber(),
                    resolutionVO.getResolutionDetailVO()
            );

            commandGateway.send(saveExpenditureResolutionCommand);
        } else {
            ChangeExpenditureResolutionCommand
                    saveExpenditureResolutionCommand = new ChangeExpenditureResolutionCommand(
                    resolutionVO.getResolutionId(),
                    resolutionVO.getResolutionDate(),
                    resolutionVO.getResolutionNumber(),
                    resolutionVO.getApplicant(),
                    resolutionVO.getApplicationAmount(),
                    resolutionVO.getSummary(),
                    resolutionVO.getSummary(),
                    resolutionVO.getApplicationCategory(),
                    resolutionVO.getElectronicPaymentNumber(),
                    resolutionVO.getResolutionDetailVO()
            );

            commandGateway.send(saveExpenditureResolutionCommand);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
