package com.thinkgem.jeesite.ym.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.CollectContentData;

@MyBatisDao
public interface CollectContentDataExtMapper extends CollectContentDataMapper {

	int deleteById(String id);

	List<CollectContentData> findList(Map<String, Object> params);

	Long findListCount(Map<String, Object> params);
}