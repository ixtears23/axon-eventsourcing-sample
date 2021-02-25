package com.ibdata.eventsourcing.acc.resolution.mapper;

import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolution;

import java.util.List;

public interface ResolutionQueryMapper {
    public TbResolution findById(TbResolution tbResolution);
    public List<TbResolution> findByApplicant(TbResolution tbResolution);
    public String findMaxId(String resolutionDate);
}
