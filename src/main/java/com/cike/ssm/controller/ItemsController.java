package com.cike.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.ModelAndView;

import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;
import com.cike.ssm.service.ItemsService;

@Controller
@RequestMapping("/item")
public class ItemsController {

	// 注入service
	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception {
		System.out.println("id=" + request.getParameter("id"));
		// 调用service查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		// 转发到jsp页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("itemsList");
		return modelAndView;
	}

	// 商品修改页面展示
	@RequestMapping(value = "/editItem", method = RequestMethod.GET)
	public ModelAndView editItem(@RequestParam(value = "id") Integer id) throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		// 转发到jsp页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", itemsCustom);
		modelAndView.setViewName("editItem");
		return modelAndView;
	}

	// 商品修改页面展示
	// 返回字符串，字符串就是逻辑视图名，Model作用是将数据填充到request域，在页面展示
	@RequestMapping(value = "/editItem1", method = RequestMethod.GET)
	public String editItem1(Model model) throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		model.addAttribute("item", itemsCustom);
		return "editItem";
	}

	@RequestMapping(value = "/editItem2", method = RequestMethod.GET)
	public void editItem2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		request.setAttribute("item", itemsCustom);
		// 使用request转向页面，指定页面的完整路径
		request.getRequestDispatcher("/WEB-INF/jsp/editItem2.jsp").forward(request, response);
	}

	// 商品修改提交
	@RequestMapping("/editItemSubmit")
	public String editItemSubmit(ItemsCustom itemsCustom) throws Exception {
		// 调用service更新商品信息
		itemsService.updateItems(itemsCustom);
		// 重定向
		return "redirect:queryItems.action";
		// 转发
		// return "forward:queryItems.action";
	}

	// 商品修改提交
	@RequestMapping("/editItemSubmit2")
	public String editItemSubmit2(ItemsCustom itemsCustom, ItemsQueryVo itemsQueryVo) throws Exception {
		// 调用service更新商品信息
		itemsService.updateItems(itemsCustom);
		// 重定向
		return "redirect:queryItems.action";
		// 转发
		// return "forward:queryItems.action";
	}

	// 自定义属性编辑器
//	@InitBinder
//	public void initBinder(WebDataBinder binder) throws Exception {
//		// Date.class必须与controller方法形参pojo属性一致的date类型，这里是java.util.Date
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
//	}
}
