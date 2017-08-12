package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.CollectContentData;

@MyBatisDao
public interface CollectContentDataMapper {
	int deleteByPrimaryKey(String id);

	int insert(CollectContentData record);

	int insertSelective(CollectContentData record);

	CollectContentData selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CollectContentData record);

	int updateByPrimaryKeyWithBLOBs(CollectContentData record);

	int updateByPrimaryKey(CollectContentData record);
}