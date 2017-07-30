package com.thinkgem.jeesite.ym.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.Version;

@MyBatisDao
public interface VersionExtMapper extends VersionMapper {

	int deleteById(String id);

	List<Version> selectList();
}