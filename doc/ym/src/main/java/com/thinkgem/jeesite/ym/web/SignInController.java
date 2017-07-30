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
 * 签到页
 *
 * @author zx
 * @Date 2017年7月15日 下午1:08:17
 */
@Controller
@RequestMapping("${adminPath}/signIn")
public class SignInController extends BaseController {

	@Autowired
	SignInService signInService;

	@ModelAttribute("signIn")
	public SignIn get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return signInService.get(id);
		} else {
			return new SignIn();
		}
	}

	@RequestMapping(value = "form")
	public String form(Model model) {
		SignIn signIn = signInService.getByType("signIn");
		model.addAttribute("signIn", signIn);
		return "ym/signIn";
	}

	@RequestMapping(value = "save")
	public String save(SignIn signIn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, signIn)) {
			return form(model);
		}
		signIn.setUpdateDate(new Date());
		signIn.setUpdateBy(UserUtils.getUser().getId());
		signInService.update(signIn);
		addMessage(redirectAttributes, "保存签到页成功");
		return "redirect:" + adminPath + "/signIn/form?repage";
	}
}
