package com.jti.event.admin.mapper.event.board;

import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import com.jti.event.admin.model.event.Store;
import com.jti.event.admin.model.event.StoreParam;
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
     * * 이벤트 참여 삭제 처리
     * */
    public int deleteList(EventBoard param);

    /**
     * 별점
     * */
    public int updateLike(EventBoard param);

    /**
     * 이미지 다운로드
     * */
    public List<EventBoard> selectImg(EventBoardParam eventBoardParam);

    /**
     * 점포등록
     * */
    int insertStore(Store store);

    /**
     * 점포목록
     * */
    public List<Store> listStore(StoreParam param);

    /**
     * 점포 카운트
     * */
    public int countStore(StoreParam param);
}
