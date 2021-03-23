package com.emyus.entity;

import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;

//Entity = beanの役割をしている
public class Question {
	
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;
//	private List<CorrectAnswer>  correctAnswers = new ArrayList<>();
//	
//	public static class CorrectAnswer {
//		private int id;
//		private int questions_id;
//		private String answer;
//		private Timestamp created_at;
//		private Timestamp updated_at;
//		
//		public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//		}
//		public int getQuestions_id() {
//			return questions_id;
//		}
//		public void setQuestions_id(int questions_id) {
//			this.questions_id = questions_id;
//		}
//		public String getAnswer() {
//			return answer;
//		}
//		public void setAnswer(String answer) {
//			this.answer = answer;
//		}
//		public Timestamp getCreated_at() {
//			return created_at;
//		}
//		public void setCreated_at(Timestamp created_at) {
//			this.created_at = created_at;
//		}
//		public Timestamp getUpdated_at() {
//			return updated_at;
//		}
//		public void setUpdated_at(Timestamp updated_at) {
//			this.updated_at = updated_at;
//		}
//	}
//	
	//Questionのgetterとsetter
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
	public Timestamp getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
//	public List<CorrectAnswer> getCorrectAnswers() {
//		return correctAnswers;
//	}
//	public void setCorrectAnswers(List<CorrectAnswer> correctAnswers) {
//		this.correctAnswers = correctAnswers;
//	}
	
}
