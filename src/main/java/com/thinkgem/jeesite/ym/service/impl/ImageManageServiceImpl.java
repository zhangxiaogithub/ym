package com.thinkgem.jeesite.ym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.ym.dao.ImageManageExtMapper;
import com.thinkgem.jeesite.ym.entity.ImageManage;
import com.thinkgem.jeesite.ym.service.ImageManageService;

/**
 * 签到service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
@Service
public class ImageManageServiceImpl implements ImageManageService {

	@Autowired
	ImageManageExtMapper imageManageExtMapper;

	@Override
	public ImageManage get(String id) {
		return imageManageExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public ImageManage getByType(String type) {
		List<ImageManage> list = imageManageExtMapper.selectList(type);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return new ImageManage();
	}

	@Override
	public int update(ImageManage record) {
		return imageManageExtMapper.updateByPrimaryKeySelective(record);
	}
}
