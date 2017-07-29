package com.thinkgem.jeesite.ym.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.SignIn;

@MyBatisDao
public interface SignInExtMapper extends SignInMapper {

	List<SignIn> selectList(String type);
}