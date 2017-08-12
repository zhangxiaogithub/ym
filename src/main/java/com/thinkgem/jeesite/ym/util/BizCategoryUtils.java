/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.ym.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.ym.entity.BizCategory;
import com.thinkgem.jeesite.ym.entity.CollectContent;
import com.thinkgem.jeesite.ym.service.BizCategoryService;
import com.thinkgem.jeesite.ym.service.CollectContentService;

/**
 * 类目工具类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
public class BizCategoryUtils {

	private static BizCategoryService bizCategoryService = SpringContextHolder.getBean(BizCategoryService.class);
	private static CollectContentService collectContentService = SpringContextHolder
			.getBean(CollectContentService.class);

	public static List<BizCategory> getBizCategoryType(String type) {
		List<BizCategory> list = Lists.newArrayList();
		for (BizCategory bizCategory : bizCategoryService.getBizCategoryType()) {
			list.add(bizCategory);
		}
		return list;
	}

	public static List<CollectContent> getCollectContent(String type) {
		List<CollectContent> list = Lists.newArrayList();
		for (CollectContent collectContent : collectContentService.getCollectContent()) {
			list.add(collectContent);
		}
		return list;
	}
}
