package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ChangeExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ReadAndQueryCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.RequestCreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.TrackingCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ReadAndQueryVO;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionVO;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.TrackingVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/expenditure/resolution")
@Slf4j
public class ExpenditureResolutionController {

    private final CommandGateway commandGateway;

    public ExpenditureResolutionController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/readandquery")
    public ResponseEntity<?> readAndQuery(@RequestBody ReadAndQueryVO readAndQueryVO) {

        CompletableFuture<Object> send = commandGateway.send(new ReadAndQueryCommand(readAndQueryVO.getResolutionId(), readAndQueryVO.getFirstSequenceNumber()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/tracking")
    public ResponseEntity<?> tracking(@RequestBody TrackingVO trackingVO) {

        CompletableFuture<Object> send = commandGateway.send(new TrackingCommand(trackingVO.getTrackingEventProcessorName(), trackingVO.getIndex()));

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveResolution(@RequestBody ResolutionVO resolutionVO) {

        if (StringUtils.isBlank(resolutionVO.getResolutionId())) {
            RequestCreateExpenditureResolutionCommand saveExpenditureResolutionCommand = new RequestCreateExpenditureResolutionCommand(
                    resolutionVO.getResolutionId(),
                    resolutionVO.getResolutionDate(),
                    resolutionVO.getResolutionNumber(),
                    resolutionVO.getApplicant(),
                    resolutionVO.getApplicationDepartment(),
                    resolutionVO.getApplicationAmount(),
                    resolutionVO.getSummary(),
                    resolutionVO.getApplicationCategory(),
                    resolutionVO.getElectronicPaymentNumber(),
                    resolutionVO.getResolutionDetailVO()
            );

            CompletableFuture<Object> send = commandGateway.send(saveExpenditureResolutionCommand);
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

            CompletableFuture<Object> send = commandGateway.send(saveExpenditureResolutionCommand);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
