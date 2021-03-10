package com.ibdata.eventsourcing.acc.resolution.controller;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ChangeExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.ReadAndQueryCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.RequestCreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.TrackingCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ReadAndQueryDTO;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDTO;
import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.TrackingDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<?> readAndQuery(@RequestBody ReadAndQueryDTO readAndQueryDTO) {

        CompletableFuture<Object> send = commandGateway.send(new ReadAndQueryCommand(readAndQueryDTO.getResolutionId(), readAndQueryDTO.getFirstSequenceNumber()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/tracking")
    public ResponseEntity<?> tracking(@RequestBody TrackingDTO trackingDTO) {

        CompletableFuture<Object> send = commandGateway.send(new TrackingCommand(trackingDTO.getTrackingEventProcessorName(), trackingDTO.getIndex()));

        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/save")
    public ResponseEntity<?> saveResolution(@RequestBody ResolutionDTO resolutionDTO) throws Exception {

        if (StringUtils.isBlank(resolutionDTO.getResolutionId())) {
            RequestCreateExpenditureResolutionCommand saveExpenditureResolutionCommand = new RequestCreateExpenditureResolutionCommand(
                    resolutionDTO.getResolutionId(),
                    resolutionDTO.getResolutionDate(),
                    resolutionDTO.getResolutionNumber(),
                    resolutionDTO.getApplicant(),
                    resolutionDTO.getApplicationDepartment(),
                    resolutionDTO.getApplicationAmount(),
                    resolutionDTO.getSummary(),
                    resolutionDTO.getApplicationCategory(),
                    resolutionDTO.getElectronicPaymentNumber(),
                    resolutionDTO.getResolutionDetailVO()
            );

            CompletableFuture<Object> send = commandGateway.send(saveExpenditureResolutionCommand);
        } else {
            ChangeExpenditureResolutionCommand
                    saveExpenditureResolutionCommand = new ChangeExpenditureResolutionCommand(
                    resolutionDTO.getResolutionId(),
                    resolutionDTO.getResolutionDate(),
                    resolutionDTO.getResolutionNumber(),
                    resolutionDTO.getApplicant(),
                    resolutionDTO.getApplicationDepartment(),
                    resolutionDTO.getApplicationAmount(),
                    resolutionDTO.getSummary(),
                    resolutionDTO.getApplicationCategory(),
                    resolutionDTO.getElectronicPaymentNumber(),
                    resolutionDTO.getResolutionDetailVO()
            );

            final boolean BOOL = false;
            CompletableFuture<Object> send = commandGateway.send(saveExpenditureResolutionCommand);
            if (BOOL) {
                log.debug("------------------------Controller에서 강제 Execption 발생------------------------");
                throw new Exception("강제 에러!!!!");
            }
            CompletableFuture<Object> send2 = commandGateway.send(saveExpenditureResolutionCommand);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
