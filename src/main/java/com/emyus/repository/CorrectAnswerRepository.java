package com.emyus.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.entity.CorrectAnswer;

@Mapper
public interface CorrectAnswerRepository {
	// Questionに紐づくものを取得
	List<CorrectAnswer> findAll();

	// 1件検索
	CorrectAnswer select(int id);

	// 新規登録
	void ansSave(CorrectAnswer answer);

	// 更新
	void ansUpdate(CorrectAnswer answer);

	// 削除
	void ansDelete(CorrectAnswer answer);
}
