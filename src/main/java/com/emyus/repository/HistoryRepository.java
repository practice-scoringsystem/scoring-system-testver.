package com.emyus.repository;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.entity.History;

@Mapper
public interface HistoryRepository {

	void historySave(History history);
	
}
