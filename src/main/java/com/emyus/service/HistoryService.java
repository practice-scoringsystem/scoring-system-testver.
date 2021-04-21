package com.emyus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.History;
import com.emyus.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	HistoryRepository historyRepository;
	
	public void historySave(History history) {
		historyRepository.historySave(history);
	}

}
