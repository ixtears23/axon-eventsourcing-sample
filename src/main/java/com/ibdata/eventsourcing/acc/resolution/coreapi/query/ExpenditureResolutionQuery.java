package com.ibdata.eventsourcing.acc.resolution.coreapi.query;

import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDetailVO;
import lombok.*;

import java.util.List;

@Data
public class ExpenditureResolutionQuery {
    private final String resolutionId;
}
