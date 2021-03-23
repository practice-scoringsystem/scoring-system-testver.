package com.emyus.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//formオブジェクト
public class RegisterForm {
	
	@Size(min = 1, max = 500, message = "Please input 500character or less")
	@NotNull
	private String question;
	
	@Valid
	private List<AnswerForm> ansList;
	

	public List<AnswerForm> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<AnswerForm> ansList) {
		this.ansList = ansList;
	}

	public RegisterForm() {	
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


}
