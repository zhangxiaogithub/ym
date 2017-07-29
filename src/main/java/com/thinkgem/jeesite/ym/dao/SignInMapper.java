package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.SignIn;

@MyBatisDao
public interface SignInMapper {
	int deleteByPrimaryKey(String id);

	int insert(SignIn record);

	int insertSelective(SignIn record);

	SignIn selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SignIn record);

	int updateByPrimaryKey(SignIn record);
}