package com.jti.event.admin.service.event.board;

import com.jti.event.admin.mapper.event.board.EventBoardMapper;
import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import com.jti.event.admin.model.event.Store;
import com.jti.event.admin.model.event.StoreParam;
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
    public int deleteList(EventBoard param) {
        return eventBoardMapper.deleteList(param);
    }

    /**
     *별점
     * */
    public int updateLike(EventBoard param) {
        return eventBoardMapper.updateLike(param);
    }

    /**
     * 이미지 다운로드
     * */
    public List<EventBoard> selectImg(EventBoardParam eventBoardParam) {
        return eventBoardMapper.selectImg(eventBoardParam);
    }

    /**
     * 점포 등록
     * */
    public int insertStore(Store store) {
        return eventBoardMapper.insertStore(store);
    }

    /**
     * 점포 목록
     * */
    public List<Store> listStore(StoreParam param) {
        return eventBoardMapper.listStore(param);
    }

    /**
     * 점포카운트
     * */
    public int countStore(StoreParam param) {
        return eventBoardMapper.countStore(param);
    }


}
