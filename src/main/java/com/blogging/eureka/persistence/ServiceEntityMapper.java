package com.blogging.eureka.persistence;


import com.blogging.eureka.model.dto.ServiceInfoDTO;
import com.blogging.eureka.model.entity.ServiceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceEntityMapper {
    int deleteByPrimaryKey (Long id);

    int insert (ServiceEntity record);

    int insertSelective (ServiceEntity record);

    ServiceEntity selectByPrimaryKey (Long id);

    int updateByPrimaryKeySelective (ServiceEntity record);

    int updateByPrimaryKey (ServiceEntity record);

    List<ServiceEntity> selectAll();

    List<ServiceEntity> selectByName(@Param("subServiceNameList") List<String> name);

    List<ServiceEntity> selectByParams(ServiceInfoDTO dto);
}