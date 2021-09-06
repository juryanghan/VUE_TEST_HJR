package com.jti.event.front.mapper.event;

import com.jti.event.front.model.event.EventMain;
import com.jti.event.front.model.event.param.EventParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    public int eventAdd(EventParam eventParam);

    // 편의점 본사 이름 가져오기
    public List<EventMain> getConvenienceStore();
}