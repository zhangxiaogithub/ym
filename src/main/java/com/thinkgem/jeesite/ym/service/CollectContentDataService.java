package com.thinkgem.jeesite.ym.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.ym.entity.CollectContentData;

/**
 * 收录内容数据管理service
 *
 * @author zx
 * @Date 2017年8月12日 下午11:43:57
 */
public interface CollectContentDataService {

	CollectContentData get(String id);

	void save(CollectContentData record);

	int update(CollectContentData record);

	void delete(String id);

	Page<CollectContentData> findPage(Page<CollectContentData> page, CollectContentData record);
}
