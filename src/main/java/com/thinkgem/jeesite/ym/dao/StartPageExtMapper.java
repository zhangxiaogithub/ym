package com.thinkgem.jeesite.ym.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.StartPage;

@MyBatisDao
public interface StartPageExtMapper extends StartPageMapper {

	/**
	 * 查询启动页列表
	 */
	List<StartPage> selectStartPageList();
}