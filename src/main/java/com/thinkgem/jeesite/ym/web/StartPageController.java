package com.thinkgem.jeesite.ym.web;

import java.util.Date;

import javax.naming.NoPermissionException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ym.common.ResultInfo;
import com.thinkgem.jeesite.ym.entity.StartPage;
import com.thinkgem.jeesite.ym.service.StartPageService;

/**
 * 启动页
 *
 * @author zx
 * @Date 2017年7月15日 下午1:08:17
 */
@Controller
@RequestMapping("${adminPath}/startPage")
public class StartPageController extends BaseController {

	@Autowired
	StartPageService startPageService;

	@ModelAttribute("startPage")
	public StartPage get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return startPageService.get(id);
		} else {
			return new StartPage();
		}
	}

	@RequestMapping(value = "form")
	public String form(Model model) {
		StartPage startPage = startPageService.get();
		model.addAttribute("startPage", startPage);
		return "ym/startPage";
	}

	@RequestMapping(value = "save")
	public String save(StartPage startPage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, startPage)) {
			return form(model);
		}
		startPage.setUpdateDate(new Date());
		startPage.setUpdateBy(UserUtils.getUser().getId());
		startPageService.save(startPage);
		addMessage(redirectAttributes, "保存启动页成功");
		return "redirect:" + adminPath + "/startPage/form?repage";
	}

	/**
	 * 查询管理员列表
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String detail() {
		return JSON.toJSONString(ResultInfo.get().setData(startPageService.getStartPage()));
	}

	/**
	 * 修改启动页
	 *
	 * @throws NoPermissionException
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String edit(@RequestParam(required = false) String id, @RequestParam(required = false) Integer time,
			@RequestParam(required = false) String url, @RequestParam(required = false) String jump,
			@RequestParam(required = false) String image) throws NoPermissionException {
		StartPage sp = new StartPage();
		User user = UserUtils.getUser();
		if (user != null && StringUtils.isNoneBlank(user.getId())) {
			sp.setId(user.getId());
			sp.setUpdateBy(user.getId());
			sp.setUpdateDate(new Date());
			sp.setUrl(url);
			sp.setTime(time);
			sp.setJump(jump);
			sp.setImage(image);
			startPageService.update(sp);
		}
		return "";
	}
}
