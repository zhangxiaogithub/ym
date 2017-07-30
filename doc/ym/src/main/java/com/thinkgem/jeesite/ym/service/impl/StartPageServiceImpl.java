package com.thinkgem.jeesite.ym.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.ym.dao.StartPageExtMapper;
import com.thinkgem.jeesite.ym.entity.StartPage;
import com.thinkgem.jeesite.ym.service.StartPageService;

/**
 * 启动页service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
@Service
public class StartPageServiceImpl implements StartPageService {

	@Autowired
	StartPageExtMapper startPageExtMapper;

	@Override
	public Map<String, Object> getStartPage() {
		Map<String, Object> map = null;
		List<StartPage> list = startPageExtMapper.selectStartPageList();
		if (list != null && list.size() > 0) {
			StartPage page = list.get(0);
			map = new HashMap<String, Object>();
			map.put("url", page.getUrl() != null ? page.getUrl() : "");
			map.put("jump", page.getJump() != null ? page.getJump() : "");
			map.put("time", page.getTime() != null ? page.getTime() : "");
			map.put("image", page.getImage() != null ? page.getImage() : "");
			map.put("id", page.getId());
		}
		return map;
	}

	@Override
	public int update(StartPage record) {
		return startPageExtMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public StartPage get(String id) {
		return startPageExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public StartPage get() {
		List<StartPage> list = startPageExtMapper.selectStartPageList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return new StartPage();
	}

	@Override
	public void save(StartPage record) {
		startPageExtMapper.insertSelective(record);
	}
}
