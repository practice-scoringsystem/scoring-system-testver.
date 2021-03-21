package com.emyus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.entity.Question;
import com.emyus.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
    QuestionRepository questionRepository;
	
	//全件取得
    public List<Question> getQuestionList(){
        return this.questionRepository.findAll();
    }
    
    public void save(Question question) {
        questionRepository.save(question);
    }
}
