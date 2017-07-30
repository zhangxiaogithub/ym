package com.thinkgem.jeesite.ym.service;

import com.thinkgem.jeesite.ym.entity.ImageManage;

/**
 * 图片管理service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
public interface ImageManageService {

	ImageManage get(String id);

	int update(ImageManage record);

	ImageManage getByType(String type);
}
