package zb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zb.model.CarModelInfo;
import zb.model.CarModelInfoExample;

public interface CarModelInfoMapper {
    long countByExample(CarModelInfoExample example);

    int deleteByExample(CarModelInfoExample example);

    int insert(CarModelInfo row);

    int insertSelective(CarModelInfo row);

    List<CarModelInfo> selectByExample(CarModelInfoExample example);

    int updateByExampleSelective(@Param("row") CarModelInfo row, @Param("example") CarModelInfoExample example);

    int updateByExample(@Param("row") CarModelInfo row, @Param("example") CarModelInfoExample example);
}