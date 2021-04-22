package com.emyus.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.emyus.entity.History;

@Mapper
public interface HistoryRepository {

	// 全件取得
	List<History> historyAll();

	// 履歴登録
	void historySave(History history);
	
	//ログインユーザーの履歴を取ってくる
	List<History> userHistoryAll(int user_id);

}
