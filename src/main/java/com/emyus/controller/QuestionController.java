package com.emyus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.emyus.entity.CorrectAnswer;
import com.emyus.entity.Question;
import com.emyus.service.CorrectAnswerService;
import com.emyus.service.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	CorrectAnswerService correctAnswerService;

	@GetMapping("list")
	public String list(Model model) {

		// 一覧表示ボタンが押されると一覧をmodelに登録。model = requestと同じ
		List<Question> qList = questionService.getQuestionList();
		model.addAttribute("qList", qList);

		// 一覧表示ボタンが押されると一覧をmodelに登録。
		List<CorrectAnswer> aList = correctAnswerService.getCorrectAnswerList();
		model.addAttribute("aList", aList);
		
		return "list";
	}
}
