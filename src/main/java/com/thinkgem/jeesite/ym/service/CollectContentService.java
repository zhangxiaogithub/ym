package com.thinkgem.jeesite.ym.service;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.ym.entity.CollectContent;

/**
 * 业务类目service
 *
 * @author zx
 * @Date 2017年8月12日 下午11:43:57
 */
public interface CollectContentService {

	CollectContent get(String id);

	void save(CollectContent record);

	int update(CollectContent record);

	void delete(String id);

	Page<CollectContent> findPage(Page<CollectContent> page, CollectContent collectContent);

	List<CollectContent> getCollectContent();
}
