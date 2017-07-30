package com.thinkgem.jeesite.ym.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ym.entity.Version;
import com.thinkgem.jeesite.ym.service.VersionService;
import com.thinkgem.jeesite.ym.util.UUUtil;

/**
 * 图标
 *
 * @author zx
 * @Date 2017年7月15日 下午1:08:17
 */
@Controller
@RequestMapping("${adminPath}/version")
public class VersionController extends BaseController {

	@Autowired
	VersionService versionService;

	@ModelAttribute("version")
	public Version get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return versionService.get(id);
		} else {
			return new Version();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Version> list = versionService.selectList();
		model.addAttribute("list", list);
		return "ym/versionList";
	}

	@RequestMapping(value = "form")
	public String form(Version version, Model model) {
		if(version != null && version.getUpdateFlag() == null) {
			version.setUpdateFlag("0");
		}
		model.addAttribute("version", version);
		return "ym/versionForm";
	}

	@RequestMapping(value = "update")
	public String update(Version version, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, version)) {
			return form(version, model);
		}
		version.setUpdateDate(new Date());
		version.setUpdateBy(UserUtils.getUser().getId());
		versionService.update(version);
		addMessage(redirectAttributes, "保存版本成功");
		return "redirect:" + adminPath + "/version/list?repage";
	}

	@RequestMapping(value = "save")
	public String save(Version version, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, version)) {
			return form(version, model);
		}
		if (version == null) {
			addMessage(redirectAttributes, "请求错误");
			return "redirect:" + adminPath + "/version/list?repage";
		}
		if (StringUtils.isNotBlank(version.getId())) {
			version.setUpdateDate(new Date());
			version.setUpdateBy(UserUtils.getUser().getId());
			versionService.update(version);
		} else {
			version.setId(UUUtil.getUUId());
			version.setDelFlag("0");
			version.setUpdateDate(new Date());
			version.setUpdateBy(UserUtils.getUser().getId());
			version.setCreateDate(version.getUpdateDate());
			version.setCreateBy(version.getUpdateBy());
			versionService.save(version);
		}
		addMessage(redirectAttributes, "保存版本成功");
		return "redirect:" + adminPath + "/version/list?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Version version, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/version/list?repage";
		}
		if (version == null) {
			addMessage(redirectAttributes, "操作错误");
			return "redirect:" + adminPath + "/version/list?repage";
		}
		versionService.delete(version.getId());
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:" + adminPath + "/version/list?repage";
	}
}
