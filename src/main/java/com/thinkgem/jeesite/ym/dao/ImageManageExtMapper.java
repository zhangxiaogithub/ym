package com.thinkgem.jeesite.ym.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.ImageManage;

@MyBatisDao
public interface ImageManageExtMapper extends ImageManageMapper {

	List<ImageManage> selectList(String type);

}