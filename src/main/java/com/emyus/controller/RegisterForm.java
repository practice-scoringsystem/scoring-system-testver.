package com.emyus.controller;

import java.util.List;

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
	
//	
//	@NotNull
//	@Size(min = 1, max = 200, message = "Please input 200character or less")
//	private String answer0;
//	
//	@NotNull
//	@Size(min = 1, max = 200, message = "Please input 200character or less")
//	private String answer1;
//	
//	public RegisterForm() {
//	}
//	
//	@Valid
//	private List<AnswerForm> ansList;
//	
//
//	public List<AnswerForm> getAnsList() {
//		return ansList;
//	}
//
//	public void setAnsList(List<AnswerForm> ansList) {
//		this.ansList = ansList;
//	}
//
//	public String getAnswer0() {
//		return answer0;
//	}
//
//	public void setAnswer0(String answer0) {
//		this.answer0 = answer0;
//	}
//
//	public String getAnswer1() {
//		return answer1;
//	}
//
//	public void setAnswer1(String answer1) {
//		this.answer1 = answer1;
//	}

}
