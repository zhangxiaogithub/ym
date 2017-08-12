package com.thinkgem.jeesite.ym.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.BizCategory;

@MyBatisDao
public interface BizCategoryExtMapper extends BizCategoryMapper {

	int deleteById(String id);

	List<BizCategory> findList(Map<String, Object> params);

	Long findListCount(Map<String, Object> params);

	List<BizCategory> getBizCategoryType(Map<String, Object> params);

}