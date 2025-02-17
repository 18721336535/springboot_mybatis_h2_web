package zb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zb.model.MobileFoodFacilityPermit;
import zb.model.MobileFoodFacilityPermitExample;

public interface MobileFoodFacilityPermitMapper {
    long countByExample(MobileFoodFacilityPermitExample example);

    int deleteByExample(MobileFoodFacilityPermitExample example);

    int insert(MobileFoodFacilityPermit row);

    int insertSelective(MobileFoodFacilityPermit row);

    List<MobileFoodFacilityPermit> selectByExample(MobileFoodFacilityPermitExample example);

    int updateByExampleSelective(@Param("row") MobileFoodFacilityPermit row, @Param("example") MobileFoodFacilityPermitExample example);

    int updateByExample(@Param("row") MobileFoodFacilityPermit row, @Param("example") MobileFoodFacilityPermitExample example);
}