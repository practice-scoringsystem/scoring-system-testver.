package com.emyus.entity;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CorrectAnswer {

	private int id;
	
	@NotNull
	private int questions_id;
	
	@NotNull
	@Size(min = 1, max = 200, message = "Please input 200character or less")
	private String answer;
	
	private Timestamp created_at;
	
	private Timestamp updated_at;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionsId() {
		return questions_id;
	}

	public void setQuestionsId(int questions_id) {
		this.questions_id = questions_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}