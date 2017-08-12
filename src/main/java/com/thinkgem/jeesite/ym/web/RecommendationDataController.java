package com.thinkgem.jeesite.ym.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.ym.entity.RecommendationData;
import com.thinkgem.jeesite.ym.service.RecommendationDataService;

/**
 * 推荐数据
 * 
 * @author zhangxiao
 *
 */
@Controller
@RequestMapping("${adminPath}/recommendationData")
public class RecommendationDataController extends BaseController {

	@Autowired
	RecommendationDataService recommendationDataService;

	@ModelAttribute("recommendationData")
	public RecommendationData get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return recommendationDataService.get(id);
		} else {
			return new RecommendationData();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(RecommendationData recommendationData, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<RecommendationData> page = recommendationDataService
				.findPage(new Page<RecommendationData>(request, response), recommendationData);
		model.addAttribute("page", page);
		return "ym/recommendationDataList";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String update(String id, Model model, RedirectAttributes redirectAttributes) {
		int update = recommendationDataService.updateStatus(id, UserUtils.getUser().getId(), new Date());
		Map<String, Object> map = Maps.newHashMap();
		if (update > 0) {
			map.put("code", "200");
			map.put("message", "OK");
		} else {
			map.put("code", "400");
			map.put("message", "更新失败");
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "delete")
	public String delete(RecommendationData recommendationData, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/recommendationData/list?repage";
		}
		if (recommendationData == null) {
			addMessage(redirectAttributes, "操作错误");
			return "redirect:" + adminPath + "/recommendationData/list?repage";
		}
		recommendationDataService.delete(recommendationData.getId());
		addMessage(redirectAttributes, "删除推荐数据成功");
		return "redirect:" + adminPath + "/recommendationData/list?repage";
	}
}
