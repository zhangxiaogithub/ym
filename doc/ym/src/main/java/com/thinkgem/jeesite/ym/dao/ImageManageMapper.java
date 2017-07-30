package com.thinkgem.jeesite.ym.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.ImageManage;

@MyBatisDao
public interface ImageManageMapper {
	int deleteByPrimaryKey(String id);

	int insert(ImageManage record);

	int insertSelective(ImageManage record);

	ImageManage selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(ImageManage record);

	int updateByPrimaryKey(ImageManage record);
}