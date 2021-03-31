package com.emyus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String registerForm(RegisterForm registerFrom, AnswerForm answerForm, Model model,
			// registerが完了したらメッセージを受け取る
			@ModelAttribute("register") String register) {
		model.addAttribute("title", "新規登録画面");
		return "new";
	}

	@PostMapping("/new")
	public String registerFormBack(RegisterForm registerFrom, AnswerForm answerForm, Model model) {
		model.addAttribute("title", "新規登録画面");
		return "new";
	}

	/**
	 * 確認画面へ送る
	 */
	@PostMapping("/registerConfirm")
	public String registerConfirm(@Validated RegisterForm registerForm, AnswerForm answerForm, BindingResult result,
			Model model) {
		// BindingResultにバリデーションの結果が入ってくるのでtrueかfalseの場合で分岐

		if (result.hasErrors()) {
			model.addAttribute("title", "新規登録画面");
			return "redirect:/new";
		}
		model.addAttribute("registerForm", registerForm);
		model.addAttribute("answerForm", answerForm);
		model.addAttribute("title", "登録確認画面");
		return "registerConfirm";
	}

	/**
	 * 新規作成画面の挿入処理
	 */
	@PostMapping("/register")
	public String register(@Validated RegisterForm registerForm, AnswerForm answerForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("title", "登録画面");
			return "redirect:/new";
		}

		Question question = makeQuestion(registerForm);
		questionService.save(question);

		CorrectAnswer ca = makeCA(answerForm);
		correctAnswerService.ansSave(ca);
		// FlashAttributeを使う リクエストを隔ててデータが保管できる。"Registerd!"が表示されたらsessionが破棄される
		redirectAttributes.addFlashAttribute("register", "Registerd!");
		return "redirect:/list";
	}

	/**
	 * 編集画面を表示
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable int id, RegisterForm registerForm, AnswerForm answerForm, Model model) {
		Question question = questionService.select(id);
		registerForm.setId(question.getId());
		registerForm.setQuestion(question.getQuestion());
		
		CorrectAnswer answer = correctAnswerService.select(id);
		answerForm.setQuestionsId(answer.getQuestionsId());
		answerForm.setAnswer(answer.getAnswer());
		
		model.addAttribute("registerForm", registerForm);
		model.addAttribute("answerForm", answerForm);
		return "edit";
	}

	// 戻るボタン用、formからQuestionに詰め替えないといけないかもprivateでメソッド作ってもいい
	//今のところ機能してない
	@PostMapping("/{id}/edit")
	public String editBack(@PathVariable int id, RegisterForm registerFrom, AnswerForm answerForm, Model model) {
		Question question = questionService.select(id);
		registerFrom.setId(question.getId());
		registerFrom.setQuestion(question.getQuestion());
		
		CorrectAnswer answer = correctAnswerService.select(id);
		answerForm.setQuestionsId(answer.getQuestionsId());
		answerForm.setAnswer(answer.getAnswer());
		
		model.addAttribute("registerForm", registerFrom);
		model.addAttribute("answerForm", answerForm);
		return "edit";
	}

	/**
	 * 編集確認画面を表示
	 */
	@PostMapping("/editConfirm")
	public String editConfirm(@Validated RegisterForm registerForm, AnswerForm answerForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/edit";
		}
		model.addAttribute("registerForm", registerForm);
		model.addAttribute("answerForm", answerForm);
		return "editConfirm";
	}

	/**
	 * 編集画面の更新処理
	 */
	@PostMapping("/update")
	public String update(RegisterForm registerForm, AnswerForm answerForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/edit";
		}
		Question question = new Question();
		question.setId(registerForm.getId());
		System.out.println(registerForm.getId());
		question.setQuestion(registerForm.getQuestion());
		questionService.update(question);
		
		CorrectAnswer answer = new CorrectAnswer();
		answer.setQuestionsId(answerForm.getQuestionsId());
		System.out.println(answerForm.getQuestionsId());
		answer.setAnswer(answerForm.getAnswer());
		correctAnswerService.ansUpdate(answer);
		
		return "redirect:/list";
	}

	/**
	 * 削除確認画面を表示
	 */
	@GetMapping("/{id}/deleteConfirm")
	public String deleteConfirm(@PathVariable int id, RegisterForm registerForm, Model model) {
		Question question = questionService.select(id);
		registerForm.setId(question.getId());
		registerForm.setQuestion(question.getQuestion());
		model.addAttribute("registerForm", registerForm);
		return "deleteConfirm";
	}
	
	/**
	 * 削除
	 */
	@PostMapping("/delete")
    public String delete(RegisterForm registerForm) {
		Question question = new Question();
		question.setId(registerForm.getId());
		questionService.delete(question);
        return "redirect:/list";
    }

	private Question makeQuestion(RegisterForm registerForm) {
		Question question = new Question();
		question.setQuestion(registerForm.getQuestion());
		return question;
	}

	private CorrectAnswer makeCA(AnswerForm answerForm) {
		CorrectAnswer answer = new CorrectAnswer();
		answer.setAnswer(answerForm.getAnswer());
		return answer;
	}

}
