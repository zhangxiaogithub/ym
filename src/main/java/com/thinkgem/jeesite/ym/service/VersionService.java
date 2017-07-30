package com.thinkgem.jeesite.ym.service;

import java.util.List;

import com.thinkgem.jeesite.ym.entity.Version;

/**
 * 笨笨管理service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
public interface VersionService {

	Version get(String id);

	void save(Version record);

	int update(Version record);

	List<Version> selectList();

	void delete(String id);
}
