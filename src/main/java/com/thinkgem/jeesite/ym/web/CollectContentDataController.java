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
import com.thinkgem.jeesite.ym.entity.CollectContent;
import com.thinkgem.jeesite.ym.entity.CollectContentData;
import com.thinkgem.jeesite.ym.service.CollectContentDataService;
import com.thinkgem.jeesite.ym.service.CollectContentService;
import com.thinkgem.jeesite.ym.util.UUUtil;

/**
 * 收录数据配置
 * 
 * @author zhangxiao
 *
 */
@Controller
@RequestMapping("${adminPath}/collectContentData")
public class CollectContentDataController extends BaseController {

	@Autowired
	CollectContentService collectContentService;
	@Autowired
	CollectContentDataService collectContentDataService;

	@ModelAttribute("collectContentData")
	public CollectContentData get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return collectContentDataService.get(id);
		} else {
			return new CollectContentData();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(CollectContentData collectContentData, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CollectContentData> page = collectContentDataService
				.findPage(new Page<CollectContentData>(request, response), collectContentData);
		model.addAttribute("page", page);
		return "ym/collectContentDataList";
	}

	@RequestMapping(value = "form")
	public String form(CollectContentData collectContentData, Model model) {
		model.addAttribute("collectContentData", collectContentData);
		return "ym/collectContentDataForm";
	}

	@RequestMapping(value = "update")
	public String update(CollectContentData collectContentData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, collectContentData)) {
			return form(collectContentData, model);
		}
		collectContentData.setUpdateDate(new Date());
		collectContentData.setUpdateBy(UserUtils.getUser().getId());
		collectContentDataService.update(collectContentData);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/collectContentData/list";
	}

	@RequestMapping(value = "save")
	public String save(CollectContentData collectContentData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, collectContentData)) {
			return form(collectContentData, model);
		}
		if (collectContentData == null) {
			addMessage(redirectAttributes, "请求错误");
			return "redirect:" + adminPath + "/collectContentData/list";
		}
		if (collectContentData.getBizCategoryId() != null) {
			CollectContent collectContent = collectContentService.get(collectContentData.getBizCategoryId());
			if (collectContent == null) {
				addMessage(redirectAttributes, "请求错误");
				return "redirect:" + adminPath + "/collectContentData/list";
			}
			collectContentData.setCollectContentTitle(collectContent.getContentTitle());
			collectContentData.setBizCategoryId(collectContent.getBizCategoryId());
			collectContentData.setCollectContentId(collectContent.getId());
		}
		if (StringUtils.isNotBlank(collectContentData.getId())) {
			collectContentData.setUpdateDate(new Date());
			collectContentData.setUpdateBy(UserUtils.getUser().getId());
			collectContentDataService.update(collectContentData);
		} else {
			collectContentData.setId(UUUtil.getUUId());
			collectContentData.setDelFlag("0");
			collectContentData.setUpdateDate(new Date());
			collectContentData.setUpdateBy(UserUtils.getUser().getId());
			collectContentData.setCreateDate(collectContentData.getUpdateDate());
			collectContentData.setCreateBy(collectContentData.getUpdateBy());
			collectContentDataService.save(collectContentData);
		}
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/collectContentData/list";
	}

	@RequestMapping(value = "delete")
	public String delete(CollectContentData collectContentData, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/collectContentData/list";
		}
		collectContentDataService.delete(collectContentData.getId());
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/collectContentData/list";
	}
}
