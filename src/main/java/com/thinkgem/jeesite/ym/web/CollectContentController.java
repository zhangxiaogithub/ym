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
import com.thinkgem.jeesite.ym.entity.CollectContent;
import com.thinkgem.jeesite.ym.service.BizCategoryService;
import com.thinkgem.jeesite.ym.service.CollectContentService;
import com.thinkgem.jeesite.ym.util.UUUtil;

/**
 * 业务收录配置
 * 
 * @author zhangxiao
 *
 */
@Controller
@RequestMapping("${adminPath}/collectContent")
public class CollectContentController extends BaseController {

	@Autowired
	BizCategoryService bizCategoryService;
	@Autowired
	CollectContentService collectContentService;

	@ModelAttribute("collectContent")
	public CollectContent get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return collectContentService.get(id);
		} else {
			return new CollectContent();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(CollectContent collectContent, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CollectContent> page = collectContentService.findPage(new Page<CollectContent>(request, response),
				collectContent);
		model.addAttribute("page", page);
		model.addAttribute("bizCategoryId", collectContent.getBizCategoryId());
		return "ym/collectContentList";
	}

	@RequestMapping(value = "form")
	public String form(CollectContent collectContent, Model model) {
		model.addAttribute("collectContent", collectContent);
		return "ym/collectContentForm";
	}

	@RequestMapping(value = "update")
	public String update(CollectContent collectContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, collectContent)) {
			return form(collectContent, model);
		}
		collectContent.setUpdateDate(new Date());
		collectContent.setUpdateBy(UserUtils.getUser().getId());
		collectContentService.update(collectContent);
		addMessage(redirectAttributes, "保存收录配置成功");
		return "redirect:" + adminPath + "/collectContent/list?bizCategoryId=" + collectContent.getBizCategoryId();
	}

	@RequestMapping(value = "save")
	public String save(CollectContent collectContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, collectContent)) {
			return form(collectContent, model);
		}
		if (collectContent == null) {
			addMessage(redirectAttributes, "请求错误");
			return "redirect:" + adminPath + "/collectContent/list";
		}
		if (collectContent.getBizCategoryId() != null) {
			BizCategory bizCategory = bizCategoryService.get(collectContent.getBizCategoryId());
			if (bizCategory == null) {
				addMessage(redirectAttributes, "请求错误");
				return "redirect:" + adminPath + "/collectContent/list?bizCategoryId="
						+ collectContent.getBizCategoryId();
			}
			collectContent.setCategoryTitle(bizCategory.getTitle());
			collectContent.setCategoryType(bizCategory.getType());
		}
		if (StringUtils.isNotBlank(collectContent.getId())) {
			collectContent.setUpdateDate(new Date());
			collectContent.setUpdateBy(UserUtils.getUser().getId());
			collectContentService.update(collectContent);
		} else {
			collectContent.setId(UUUtil.getUUId());
			collectContent.setDelFlag("0");
			collectContent.setUpdateDate(new Date());
			collectContent.setUpdateBy(UserUtils.getUser().getId());
			collectContent.setCreateDate(collectContent.getUpdateDate());
			collectContent.setCreateBy(collectContent.getUpdateBy());
			collectContentService.save(collectContent);
		}
		addMessage(redirectAttributes, "保存收录配置成功");
		return "redirect:" + adminPath + "/collectContent/list?bizCategoryId=" + collectContent.getBizCategoryId();
	}

	@RequestMapping(value = "delete")
	public String delete(CollectContent collectContent, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/collectContent/list?bizCategoryId=" + collectContent.getBizCategoryId();
		}
		collectContentService.delete(collectContent.getId());
		addMessage(redirectAttributes, "删除收录配置成功");
		return "redirect:" + adminPath + "/collectContent/list?bizCategoryId=" + collectContent.getBizCategoryId();
	}
}
