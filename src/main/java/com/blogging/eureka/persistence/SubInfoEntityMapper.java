package com.blogging.eureka.persistence;

import com.blogging.eureka.model.entity.SubInfoEntity;
import org.apache.ibatis.annotations.Param;

public interface SubInfoEntityMapper {
    int deleteByPrimaryKey (Long id);

    int insert (SubInfoEntity record);

    int insertSelective (SubInfoEntity record);

    SubInfoEntity selectByPrimaryKey (Long id);

    int updateByPrimaryKeySelective (SubInfoEntity record);

    int updateByPrimaryKey (SubInfoEntity record);

    SubInfoEntity selectByAddr (@Param("addr") String addr);

    int updateByIp (SubInfoEntity entity);

    int selectCountByIp (@Param("ipAddr") String ipAddr);
}