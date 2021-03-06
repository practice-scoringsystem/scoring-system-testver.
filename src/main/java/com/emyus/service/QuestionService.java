package com.emyus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.Question;
import com.emyus.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;

	// 全件取得
	public List<Question> getQuestionList() {
		return this.questionRepository.findAll();
	}

	// 全件ランダム表示
	public List<Question> getRandomQuestionList() {
		return this.questionRepository.randomAll();
	}
	
	// 登録機能
	public void save(Question question) {
		questionRepository.save(question);
	}

	// 1件検索
	public Question select(int id) {
		return questionRepository.select(id);
	}

	// 更新
	public void update(Question question) {
		questionRepository.update(question);
	}

	// 削除
	public void delete(Question question) {
		questionRepository.delete(question);
	}
}
