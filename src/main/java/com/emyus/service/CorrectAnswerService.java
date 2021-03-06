package com.emyus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.CorrectAnswer;
import com.emyus.repository.CorrectAnswerRepository;

@Service
public class CorrectAnswerService {
	@Autowired
	CorrectAnswerRepository correctAnswerRepository;

	// 全件取得
	public List<CorrectAnswer> getCorrectAnswerList() {
		return this.correctAnswerRepository.findAll();
	}

	// Questionsに紐づく1件検索
	public CorrectAnswer select(int id) {
		return correctAnswerRepository.select(id);
	}

	// correct_answersを1件検索
	public CorrectAnswer ansSelect(int id) {
		return correctAnswerRepository.ansSelect(id);
	}

	// 登録機能
	public void ansSave(CorrectAnswer answer) {
		correctAnswerRepository.ansSave(answer);
	}

	// 更新
	public void ansUpdate(CorrectAnswer answer) {
		correctAnswerRepository.ansUpdate(answer);
	}

	// 削除
	public void ansDelete(CorrectAnswer answer) {
		correctAnswerRepository.ansDelete(answer);
	}

}
