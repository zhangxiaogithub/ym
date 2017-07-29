package com.thinkgem.jeesite.ym.service;

import com.thinkgem.jeesite.ym.entity.SignIn;

/**
 * 启动页service
 *
 * @author zx
 * @Date 2017年7月15日 下午9:11:57
 */
public interface SignInService {

	SignIn get(String id);

	void save(SignIn record);

	SignIn getByType(String type);
}
