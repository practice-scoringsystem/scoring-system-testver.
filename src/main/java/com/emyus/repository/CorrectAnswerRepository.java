package com.emyus.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.entity.CorrectAnswer;

@Mapper
public interface CorrectAnswerRepository {
	//Questionに紐づくものを取得
		List<CorrectAnswer> findAll();
}
