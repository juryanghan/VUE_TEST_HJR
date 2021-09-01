package com.jti.event.front.mapper.event;

import com.jti.event.front.model.event.param.EventParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    public int eventAdd(EventParam eventParam);
}