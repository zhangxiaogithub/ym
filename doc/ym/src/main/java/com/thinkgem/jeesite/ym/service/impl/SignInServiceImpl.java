package com.thinkgem.jeesite.ym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.ym.dao.SignInExtMapper;
import com.thinkgem.jeesite.ym.entity.SignIn;
import com.thinkgem.jeesite.ym.service.SignInService;

/**
 * 签到service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
@Service
public class SignInServiceImpl implements SignInService {

	@Autowired
	SignInExtMapper signInExtMapper;

	@Override
	public SignIn get(String id) {
		return signInExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public SignIn getByType(String type) {
		List<SignIn> list = signInExtMapper.selectList(type);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return new SignIn();
	}

	@Override
	public int update(SignIn record) {
		return signInExtMapper.updateByPrimaryKeySelective(record);
	}
}
