package com.emyus.controller;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerForm {

	@NotNull
	private int id;

	@NotNull
	@Size(min = 1, max = 200, message = "Please input 200character or less")
	private String answer;
	private int result;
	private List<AnswerForm> answers;
	// 質問数と回答数表示
	private int qCount;
	private double dubqCount;
	private int count;
	private String name;

	public int getAnswerId() {
		return id;
	}

	public void setAnswerId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<AnswerForm> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerForm> answers) {
		this.answers = answers;
	}

	public int getQCount() {
		return qCount;
	}

	public void setQCount(int qCount) {
		this.qCount = qCount;
	}
	
	public double getDubqCount() {
		return dubqCount;
	}

	public double setDubqCount(double dubqCount) {
		return dubqCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	
}