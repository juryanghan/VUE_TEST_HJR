package com.jti.event.front.service.event;

import com.jti.event.front.mapper.event.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	@Autowired
	private EventMapper eventMapper;
	

}