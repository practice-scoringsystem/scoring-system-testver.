package com.emyus.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerForm {
	
	@NotNull
	@Size(min = 1, max = 200, message = "Please input 200character or less")
	private String answer;
	
//	@NotNull
//	@Size(min = 1, max = 200, message = "Please input 200character or less")
//	private String answer1;

	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}

//	public String getAnswer1() {
//		return answer1;
//	}
//
//	public void setAnswer1(String answer1) {
//		this.answer1 = answer1;
//	}

	
}