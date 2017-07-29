package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.StartPage;

@MyBatisDao
public interface StartPageMapper {
	int deleteByPrimaryKey(String id);

	int insert(StartPage record);

	int insertSelective(StartPage record);

	StartPage selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(StartPage record);

	int updateByPrimaryKey(StartPage record);
}