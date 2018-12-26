package com.blogging.eureka.service.eureka;

import com.blogging.eureka.model.entity.ServiceEntity;
import com.blogging.eureka.model.entity.SubInfoEntity;
import com.blogging.eureka.persistence.ServiceEntityMapper;
import com.blogging.eureka.persistence.SubInfoEntityMapper;
import com.blogging.eureka.support.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author techoneduan
 * @date 2018/12/19
 */

@Service
public class BSPService {
    //BSP - Blogging Service Platform

    @Autowired
    private ServiceEntityMapper serviceEntityMapper;

    @Autowired
    private SubInfoEntityMapper subInfoEntityMapper;

    public void addService (List<ServiceEntity> entityList) {
        entityList.stream().forEach(item -> {
            item.setEnable(1);
            item.setAddTime(new Date());
            item.setUpdateTime(new Date());
            serviceEntityMapper.insertSelective(item);
        });
    }

    public List<ServiceEntity> queryAll () {
        return serviceEntityMapper.selectAll();
    }

    public void addSubInfo (SubInfoEntity subInfoEntity) {
        int count = subInfoEntityMapper.selectCountByIp(subInfoEntity.getIpAddr());
        if (count > 0)
            subInfoEntityMapper.updateByIp(subInfoEntity);
        else
            subInfoEntityMapper.insertSelective(subInfoEntity);
    }
}
