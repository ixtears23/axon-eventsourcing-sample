package com.ibdata.eventsourcing.acc.resolution.service;

import com.ibdata.eventsourcing.acc.resolution.coreapi.vo.ResolutionDTO;

public interface ResolutionService {

    // 저장
    public ResolutionDTO saveResolution(ResolutionDTO resolutionDTO);

    // 승인
    public ResolutionDTO approveResolution(ResolutionDTO resolutionDTO);

    // 반려
    public ResolutionDTO rejectResolution(ResolutionDTO resolutionDTO);

    // 회수
    public ResolutionDTO recallResolution(ResolutionDTO resolutionDTO);

    // 삭제
    public ResolutionDTO deleteResolution(ResolutionDTO resolutionDTO);
}
