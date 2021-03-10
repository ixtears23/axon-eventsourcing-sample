package com.ibdata.eventsourcing.acc.resolution.handler;

import com.ibdata.eventsourcing.acc.resolution.coreapi.command.CreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.coreapi.command.RequestCreateExpenditureResolutionCommand;
import com.ibdata.eventsourcing.acc.resolution.domain.ResolutionKeyUtils;
import com.ibdata.eventsourcing.acc.resolution.mapper.ResolutionQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class ExpenditureResolutionCommandHandler {


    @CommandHandler
    public void handle(RequestCreateExpenditureResolutionCommand command, ResolutionQueryMapper resolutionQueryMapper, CommandGateway commandGateway) throws ExecutionException, InterruptedException {

//        String maxId = resolutionQueryMapper.findMaxId(ResolutionKeyUtils.getToday());
        String maxId = "22";

        commandGateway.send(new CreateExpenditureResolutionCommand(
                ResolutionKeyUtils.generateResolutionId(maxId),
                ResolutionKeyUtils.getToday(),
                ResolutionKeyUtils.generateResolutionNumber(maxId),
                command.getApplicant(),
                command.getApplicationDepartment(),
                command.getApplicationAmount(),
                command.getSummary(),
                command.getApplicationCategory(),
                command.getElectronicPaymentNumber(),
                command.getExpenditureResolutionDetailList()));

    }
}
