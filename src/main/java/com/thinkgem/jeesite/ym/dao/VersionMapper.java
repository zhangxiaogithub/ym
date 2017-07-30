package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.Version;

@MyBatisDao
public interface VersionMapper {
	int deleteByPrimaryKey(String id);

	int insert(Version record);

	int insertSelective(Version record);

	Version selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Version record);

	int updateByPrimaryKey(Version record);
}