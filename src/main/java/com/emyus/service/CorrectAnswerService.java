package com.emyus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.CorrectAnswer;
import com.emyus.entity.Question;
import com.emyus.repository.CorrectAnswerRepository;

@Service
public class CorrectAnswerService {
	@Autowired
	CorrectAnswerRepository correctAnswerRepository;

	// 全件取得
	public List<CorrectAnswer> getCorrectAnswerList() {
		return this.correctAnswerRepository.findAll();
	}

	// 1件検索
	public CorrectAnswer select(int id) {
		return correctAnswerRepository.select(id);
	}

	// 登録機能
	public void ansSave(CorrectAnswer answer) {
		correctAnswerRepository.ansSave(answer);
	}

	// 更新
	public void update(CorrectAnswer answer) {
		correctAnswerRepository.update(answer);
	}
}
