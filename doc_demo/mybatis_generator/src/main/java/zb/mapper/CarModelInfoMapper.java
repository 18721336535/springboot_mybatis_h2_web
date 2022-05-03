package zb.mapper;

import zb.model.CarModelInfo;

public interface CarModelInfoMapper {
    int insert(CarModelInfo row);

    int insertSelective(CarModelInfo row);
}