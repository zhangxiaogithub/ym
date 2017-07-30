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
import com.thinkgem.jeesite.ym.entity.ImageManage;
import com.thinkgem.jeesite.ym.service.ImageManageService;

/**
 * 图标
 *
 * @author zx
 * @Date 2017年7月15日 下午1:08:17
 */
@Controller
@RequestMapping("${adminPath}/image")
public class ImageController extends BaseController {

	@Autowired
	ImageManageService imageManageService;

	@ModelAttribute("image")
	public ImageManage get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return imageManageService.get(id);
		} else {
			return new ImageManage();
		}
	}

	@RequestMapping(value = "form")
	public String form(Model model) {
		ImageManage image = imageManageService.getByType("image");
		model.addAttribute("image", image);
		return "ym/image";
	}

	@RequestMapping(value = "save")
	public String save(ImageManage imageManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, imageManage)) {
			return form(model);
		}
		imageManage.setUpdateDate(new Date());
		imageManage.setUpdateBy(UserUtils.getUser().getId());
		imageManageService.update(imageManage);
		addMessage(redirectAttributes, "保存图片页成功");
		return "redirect:" + adminPath + "/image/form?repage";
	}
}
