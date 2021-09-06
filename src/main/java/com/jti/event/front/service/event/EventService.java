package com.jti.event.front.service.event;

import com.jti.event.front.mapper.event.EventMapper;
import com.jti.event.front.model.event.EventMain;
import com.jti.event.front.model.event.param.EventParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

	@Autowired
	private EventMapper eventMapper;

	public int eventAdd(EventParam eventParam){
		return eventMapper.eventAdd(eventParam);
	}

	// 편의점 본사명 가져오기
	public List<EventMain> getConvenienceStore(){
		return eventMapper.getConvenienceStore();
	}

}