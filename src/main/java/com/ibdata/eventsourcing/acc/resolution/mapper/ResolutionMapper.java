package com.ibdata.eventsourcing.acc.resolution.mapper;

import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolution;
import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolutionDetail;
import com.ibdata.eventsourcing.acc.resolution.coreapi.entity.TbResolutionDetailAttachment;

public interface ResolutionMapper {

    public void insertResolution(TbResolution tbResolution);
    public void updateResolution(TbResolution tbResolution);
    public void deleteResolution(TbResolution tbResolution);
    public void insertResolutionDetail(TbResolutionDetail tbResolutionDetail);
    public void updateResolutionDetail(TbResolutionDetail tbResolutionDetail);
    public void deleteResolutionDetail(TbResolutionDetail tbResolutionDetail);
    public void insertResolutionDetailAttachment(TbResolutionDetailAttachment tbResolutionDetailAttachment);
    public void updateResolutionDetailAttachment(TbResolutionDetailAttachment tbResolutionDetailAttachment);
    public void deleteResolutionDetailAttachment(TbResolutionDetailAttachment tbResolutionDetailAttachment);
}
