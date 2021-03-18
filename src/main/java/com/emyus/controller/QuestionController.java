package com.emyus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.emyus.entity.Question;
import com.emyus.service.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("list")
	public String list(Model model) {
		
		// 一覧表示ボタンが押されると一覧をmodelに登録。
		List<Question> qList = questionService.getQuestionList();
		model.addAttribute("qList", qList);
		return "list";
		
	}
}
