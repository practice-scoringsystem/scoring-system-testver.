package com.emyus.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.emyus.entity.Question;

@Mapper
public interface QuestionRepository {
	// 全件取得
	List<Question> findAll();

	// 問題ランダム表示
	List<Question> randomAll();

	// 新規登録
	void save(Question question);

	// 1件検索
	Question select(int id);

	// 更新
	void update(Question question);

	// 削除
	void delete(Question question);
}