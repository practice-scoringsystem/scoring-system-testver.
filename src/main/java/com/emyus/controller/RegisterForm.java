package com.emyus.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//formオブジェクト
public class RegisterForm {
	
	@Size(min = 1, max = 500, message = "Please input 500character or less")
	@NotNull
	private String question;

	public RegisterForm() {	
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
