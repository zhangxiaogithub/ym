package com.thinkgem.jeesite.ym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.ym.dao.VersionExtMapper;
import com.thinkgem.jeesite.ym.entity.Version;
import com.thinkgem.jeesite.ym.service.VersionService;

/**
 * 版本管理service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	VersionExtMapper versionExtMapper;

	@Override
	public Version get(String id) {
		return versionExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Version record) {
		versionExtMapper.insertSelective(record);
	}

	@Override
	public List<Version> selectList() {
		return versionExtMapper.selectList();
	}

	@Override
	public int update(Version record) {
		return versionExtMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void delete(String id) {
		versionExtMapper.deleteById(id);
	}
}
