package com.emyus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.History;
import com.emyus.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	HistoryRepository historyRepository;

	// 全件取得
	public List<History> getHistoryList() {
		return this.historyRepository.historyAll();
	}

	//採点履歴を登録
	public void historySave(History history) {
		historyRepository.historySave(history);
	}
	
	//ユーザーの採点履歴を取ってくる
	public List<History> getUserHistoryList(int user_id) {
		return this.historyRepository.userHistoryAll(user_id);
	}

}
