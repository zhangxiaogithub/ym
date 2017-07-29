package com.thinkgem.jeesite.ym.web;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ym.entity.SignIn;
import com.thinkgem.jeesite.ym.service.SignInService;

/**
 * 图标
 *
 * @author zx
 * @Date 2017年7月15日 下午1:08:17
 */
@Controller
@RequestMapping("${adminPath}/icon")
public class IconController extends BaseController {

	@Autowired
	SignInService signInService;

	@ModelAttribute("icon")
	public SignIn get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return signInService.get(id);
		} else {
			return new SignIn();
		}
	}

	@RequestMapping(value = "form")
	public String form(Model model) {
		SignIn icon = signInService.getByType("icon");
		model.addAttribute("icon", icon);
		return "ym/icon";
	}

	@RequestMapping(value = "save")
	public String save(SignIn signIn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, signIn)) {
			return form(model);
		}
		signIn.setUpdateDate(new Date());
		signIn.setUpdateBy(UserUtils.getUser().getId());
		signInService.save(signIn);
		addMessage(redirectAttributes, "保存签到页成功");
		return "redirect:" + adminPath + "/icon/form?repage";
	}
}
