package com.ibdata.eventsourcing.acc.resolution.service;

import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionVO;

public interface ResolutionService {

    // 저장
    public ResolutionVO saveResolution(ResolutionVO resolutionVO);

    // 승인
    public ResolutionVO approveResolution(ResolutionVO resolutionVO);

    // 반려
    public ResolutionVO rejectResolution(ResolutionVO resolutionVO);

    // 회수
    public ResolutionVO recallResolution(ResolutionVO resolutionVO);

    // 삭제
    public ResolutionVO deleteResolution(ResolutionVO resolutionVO);
}
