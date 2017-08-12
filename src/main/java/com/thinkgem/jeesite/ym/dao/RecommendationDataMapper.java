package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.RecommendationData;

@MyBatisDao
public interface RecommendationDataMapper {

	int deleteByPrimaryKey(String id);

	int insert(RecommendationData record);

	int insertSelective(RecommendationData record);

	RecommendationData selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(RecommendationData record);

	int updateByPrimaryKey(RecommendationData record);
}