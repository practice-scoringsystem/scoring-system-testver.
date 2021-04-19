package com.emyus.controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
		answerForm.setAnswerId(answer.getId());
		answerForm.setAnswer(answer.getAnswer());

		model.addAttribute("registerForm", registerForm);
		model.addAttribute("answerForm", answerForm);
		return "edit";
	}

	// 戻るボタン用、formからQuestionに詰め替えないといけないかもprivateでメソッド作ってもいい
	// 今のところ機能してない
	@PostMapping("/{id}/edit")
	public String editBack(@PathVariable int id, RegisterForm registerFrom, AnswerForm answerForm, Model model) {
		Question question = questionService.select(id);
		registerFrom.setId(question.getId());
		registerFrom.setQuestion(question.getQuestion());

		CorrectAnswer answer = correctAnswerService.select(id);
		answerForm.setAnswerId(answer.getId());
		answerForm.setAnswer(answer.getAnswer());

		model.addAttribute("registerForm", registerFrom);
		model.addAttribute("answerForm", answerForm);
		return "edit";
	}

	/**
	 * 編集確認画面を表示
	 */
	@PostMapping("/editConfirm")
	public String editConfirm(@Validated RegisterForm registerForm, AnswerForm answerForm, BindingResult result,
			Model model) {
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
		question.setQuestion(registerForm.getQuestion());
		questionService.update(question);

		CorrectAnswer answer = new CorrectAnswer();
		answer.setId(answerForm.getAnswerId());
		answer.setAnswer(answerForm.getAnswer());
		correctAnswerService.ansUpdate(answer);

		return "redirect:/list";
	}

	/**
	 * 削除確認画面を表示
	 */
	@GetMapping("/{id}/deleteConfirm")
	public String deleteConfirm(@PathVariable int id, RegisterForm registerForm, AnswerForm answerForm, Model model) {
		Question question = questionService.select(id);
		registerForm.setId(question.getId());
		registerForm.setQuestion(question.getQuestion());

		CorrectAnswer answer = correctAnswerService.select(id);
		answerForm.setAnswerId(answer.getId());
		answerForm.setAnswer(answer.getAnswer());

		model.addAttribute("registerForm", registerForm);
		model.addAttribute("answerForm", answerForm);
		return "deleteConfirm";
	}

	/**
	 * 削除
	 */
	@PostMapping("/delete")
	public String delete(RegisterForm registerForm, AnswerForm answerForm) {
		Question question = new Question();
		question.setId(registerForm.getId());
		questionService.delete(question);

		CorrectAnswer answer = new CorrectAnswer();
		answer.setId(answerForm.getAnswerId());
		correctAnswerService.ansDelete(answer);

		return "redirect:/list";
	}

	// テスト一覧
	@GetMapping("/test")
	public String test(@ModelAttribute AnswerForm answerForm, Model model) {
		// 一覧表示ボタンが押されると一覧をmodelに登録。model = requestと同じ
		List<Question> qList = questionService.getRandomQuestionList();

		model.addAttribute("qList", qList);
		model.addAttribute("answerForm", answerForm);
		return "test";
	}

	// フォームで入力した値をAnswerFormにいれる
	// Answerformの値とDBの値を比較
	// 同じだったら正解として１カウント
	// ユーザー名の表示をする
	@PostMapping("/testResult")
	public String testResult(HttpServletRequest request, @ModelAttribute AnswerForm answerForm, Model model) {
		
		// inputされた値を配列で受け取ります questionsのidとanswersのid
		String[] questions_ids = request.getParameterValues("id");
		String[] ans = request.getParameterValues("answer");
		//String[] ansを使える形にする
		List<String> ansList = Arrays.asList(ans);

		// ここで比較するのはinputの入力値とDBに入っている値
		// questionsのidを受け取る→DBにあるquestionsに紐づく値を探すため
		int qId[] = new int[questions_ids.length];
		String[] answers = new String[ansList.size()];
		
		double dubcount = 0;

		for (int i = 0; i < qId.length; i++) {
			qId[i] = Integer.parseInt(questions_ids[i]);
			CorrectAnswer findAns = correctAnswerService.select(qId[i]);
			List<CorrectAnswer> findAnswers = Arrays.asList(findAns);
			
			answers = new String[ansList.size()];

			for (int j = 0; j < findAnswers.size(); j++) {
				if (findAnswers.get(j) != null) {

					// answersを１つずつ詰めていく
					answers[j] = findAnswers.get(j).getAnswer();
					// もし入力の値がcorrect_answersのanswerと文字列がイコールだったらHistoriesのpointカラムを+1する

					for (int k = 0; k < ans.length; k++) {
						if (answers[j].equals(ans[k])) {
							dubcount++;
							break;
						}
					}
				}
			}
		}

		// 質問数のカウント
		double dubqCount = answerForm.setDubqCount(dubcount);
		
		int qCount = questions_ids.length;

		// 計算をする 正解数÷問題数(四捨五入をするのでdouble型になる)
		long lresult = Math.round(100 * dubqCount / qCount);
		int result = (int) lresult;

		// 質問数と回答数表示のため整数にする
		int count = (int) dubqCount;
		
		answerForm.setQCount(qCount);
		answerForm.setCount(count);
		answerForm.setResult(result);

		model.addAttribute("answerForm", answerForm);
		return "testResult";

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
