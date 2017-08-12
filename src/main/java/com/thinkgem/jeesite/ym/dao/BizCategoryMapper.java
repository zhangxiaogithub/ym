package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.BizCategory;

@MyBatisDao
public interface BizCategoryMapper {
	int deleteByPrimaryKey(String id);

	int insert(BizCategory record);

	int insertSelective(BizCategory record);

	BizCategory selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(BizCategory record);

	int updateByPrimaryKey(BizCategory record);
}