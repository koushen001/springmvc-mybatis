package com.cike.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.service.ItemsService;

@Controller
public class ItemsController {

	// 注入service
	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception {
		// 调用service查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		// 转发到jsp页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("itemsList");
		return modelAndView;
	}
}
