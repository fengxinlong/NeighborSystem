package com.neighborsystem.mapper;

import com.neighborsystem.entity.Evaluate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateMapper {
	Evaluate findEvaById(Integer id);
	Integer addEvaluate(Evaluate eva);
	Integer deleteEvaluate(Integer id);
	Integer updateEvaluate(Evaluate eva);
	List<Evaluate> findEvaByGoodsId(Integer id);
	List<Evaluate> findEvaByUserId(Integer id);
	List<Evaluate> findAllEvalute();
	List<Evaluate> findAllEvaluteLikeContent(String keyword);
}
