package com.jti.event.admin.service.event.board;

import com.jti.event.admin.mapper.event.board.EventBoardMapper;
import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBoardService {

    @Autowired
    private EventBoardMapper eventBoardMapper;

    /**
     * 이벤트 카운트
     *
     * @return int
     */
    public int countEventBoard (EventBoardParam param) {
        return eventBoardMapper.countEventBoard(param);
    }


    /**
     * 이벤트참여 리스트 화면
     * */
    public List<EventBoard> listEventBoard(EventBoardParam param) {
        return eventBoardMapper.listEventBoard(param);
    }

    /**
     * * 이벤트 참여 처리
     * */
    public int updateEventProcess(EventBoard param) {
        return eventBoardMapper.updateEventProcess(param);
    }
}
