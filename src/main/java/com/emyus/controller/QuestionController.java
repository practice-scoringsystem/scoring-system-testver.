package com.emyus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	/**
	 * 新規作成画面の表示
	 */
	@GetMapping("/new")
	public String registerForm(RegisterForm registerFrom,
			Model model,
			//registerが完了したらメッセージを受け取る
			@ModelAttribute("register") String register) {
		model.addAttribute("title", "新規登録画面");
		return "new";
	}
	
	@PostMapping("/new")
	public String registerFormBack(RegisterForm registerFrom, Model model) {
		model.addAttribute("title", "新規登録画面");
		return "new";
	}

	/**
	 * 確認画面へ送る
	 */
	@PostMapping("/registerConfirm")
	public String registerConfirm(@Validated RegisterForm registerForm,
			BindingResult result,
			Model model) {
		//BindingResultにバリデーションの結果が入ってくるのでtrueかfalseの場合で分岐
		if(result.hasErrors()) {
			model.addAttribute("title", "新規登録画面");
			return "redirect:/new";
		}
		model.addAttribute("title", "登録確認画面");
		return "registerConfirm";
	}

	/**
	 * 新規作成画面の挿入処理
	 */
	@PostMapping("/register")
	public String register(@Validated RegisterForm registerForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "登録画面");
			return "redirect:/new";
		}
		
		Question question = makeQuestion(registerForm);
		questionService.save(question);
		//FlashAttributeを使う　リクエストを隔ててデータが保管できる。"Registerd!"が表示されたらsessionが破棄される
		redirectAttributes.addFlashAttribute("register", "Registerd!");
		return "redirect:/list";
	}

	private Question makeQuestion(RegisterForm registerForm) {
		Question question = new Question();
		question.setQuestion(registerForm.getQuestion());
		return question;
	}
}
