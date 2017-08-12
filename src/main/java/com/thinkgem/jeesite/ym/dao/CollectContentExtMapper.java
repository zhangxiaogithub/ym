package com.thinkgem.jeesite.ym.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.ym.entity.CollectContent;

@MyBatisDao
public interface CollectContentExtMapper extends CollectContentMapper {

	int deleteById(String id);

	List<CollectContent> findList(Map<String, Object> params);

	Long findListCount(Map<String, Object> params);

	List<CollectContent> getCollectContent(HashMap<String, Object> hashMap);

}