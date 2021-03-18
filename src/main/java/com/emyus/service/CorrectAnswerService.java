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
}
