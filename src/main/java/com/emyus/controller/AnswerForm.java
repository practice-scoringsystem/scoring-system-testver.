package com.emyus.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerForm {

	@NotNull
	private int id;

	@NotNull
	@Size(min = 1, max = 200, message = "Please input 200character or less")
	private String answer;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}