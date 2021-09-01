package com.jti.event.admin.mapper.event.board;

import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventBoardMapper {

    /**
     * 이벤트 카운트
     *
     */
    public int countEventBoard (EventBoardParam param);

    /**
     * 이벤트 목록
     *
     */
    public List<EventBoard> listEventBoard (EventBoardParam param);

    /**
     * * 이벤트 참여 처리
     * */
    int updateEventProcess(EventBoard param);
}
