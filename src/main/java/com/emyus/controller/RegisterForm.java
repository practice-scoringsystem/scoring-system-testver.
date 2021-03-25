package com.emyus.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//formオブジェクト
public class RegisterForm {
	
	private int id;

	@Size(min = 1, max = 500, message = "Please input 500character or less")
	@NotNull
	private String question;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
