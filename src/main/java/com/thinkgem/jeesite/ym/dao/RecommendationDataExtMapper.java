package com.thinkgem.jeesite.ym.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.RecommendationData;

@MyBatisDao
public interface RecommendationDataExtMapper extends RecommendationDataMapper {

	int updateStatus(Map<String, Object> params);

	int deleteById(String id);

	List<RecommendationData> findList(Map<String, Object> params);

	Long findListCount(Map<String, Object> params);
}