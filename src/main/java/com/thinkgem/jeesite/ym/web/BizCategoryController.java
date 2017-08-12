package com.thinkgem.jeesite.ym.web;

import java.util.Date;

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
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ym.entity.BizCategory;
import com.thinkgem.jeesite.ym.service.BizCategoryService;
import com.thinkgem.jeesite.ym.util.UUUtil;

/**
 * 业务类目
 * 
 * @author zhangxiao
 *
 */
@Controller
@RequestMapping("${adminPath}/bizCategory")
public class BizCategoryController extends BaseController {

	@Autowired
	BizCategoryService bizCategoryService;

	@ModelAttribute("bizCategory")
	public BizCategory get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return bizCategoryService.get(id);
		} else {
			return new BizCategory();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(BizCategory bizCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizCategory> page = bizCategoryService.findPage(new Page<BizCategory>(request, response), bizCategory);
		model.addAttribute("page", page);
		return "ym/bizCategoryList";
	}

	@RequestMapping(value = "form")
	public String form(BizCategory bizCategory, Model model) {
		model.addAttribute("bizCategory", bizCategory);
		return "ym/bizCategoryForm";
	}

	@RequestMapping(value = "update")
	public String update(BizCategory bizCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizCategory)) {
			return form(bizCategory, model);
		}
		bizCategory.setUpdateDate(new Date());
		bizCategory.setUpdateBy(UserUtils.getUser().getId());
		bizCategoryService.update(bizCategory);
		addMessage(redirectAttributes, "保存类目成功");
		return "redirect:" + adminPath + "/bizCategory/list?repage";
	}

	@RequestMapping(value = "save")
	public String save(BizCategory bizCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizCategory)) {
			return form(bizCategory, model);
		}
		if (bizCategory == null) {
			addMessage(redirectAttributes, "请求错误");
			return "redirect:" + adminPath + "/bizCategory/list?repage";
		}
		if (StringUtils.isNotBlank(bizCategory.getId())) {
			bizCategory.setUpdateDate(new Date());
			bizCategory.setUpdateBy(UserUtils.getUser().getId());
			bizCategoryService.update(bizCategory);
		} else {
			bizCategory.setId(UUUtil.getUUId());
			bizCategory.setDelFlag("0");
			bizCategory.setUpdateDate(new Date());
			bizCategory.setUpdateBy(UserUtils.getUser().getId());
			bizCategory.setCreateDate(bizCategory.getUpdateDate());
			bizCategory.setCreateBy(bizCategory.getUpdateBy());
			bizCategoryService.save(bizCategory);
		}
		addMessage(redirectAttributes, "保存类目成功");
		return "redirect:" + adminPath + "/bizCategory/list?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(BizCategory bizCategory, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/bizCategory/list?repage";
		}
		if (bizCategory == null) {
			addMessage(redirectAttributes, "操作错误");
			return "redirect:" + adminPath + "/bizCategory/list?repage";
		}
		bizCategoryService.delete(bizCategory.getId());
		addMessage(redirectAttributes, "删除类目成功");
		return "redirect:" + adminPath + "/bizCategory/list?repage";
	}
}
