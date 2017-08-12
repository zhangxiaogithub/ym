package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.CollectContent;

@MyBatisDao
public interface CollectContentMapper {

	int deleteByPrimaryKey(String id);

	int insert(CollectContent record);

	int insertSelective(CollectContent record);

	CollectContent selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(CollectContent record);

	int updateByPrimaryKey(CollectContent record);
}