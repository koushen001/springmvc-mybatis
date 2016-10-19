package com.cike.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cike.ssm.exception.CustomException;
import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;
import com.cike.ssm.service.ItemsService;

@Controller
@RequestMapping("/item")
public class ItemsController {

	// 注入service
	@Autowired
	private ItemsService itemsService;
	
	@ModelAttribute("itemType")
	public Map<String,String> getItemType() throws Exception{
		Map<String, String> map = new HashMap<String,String>();
		map.put("1", "手机");
		map.put("2", "电脑");
		return map;
	}
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
		//测试统一异常处理
		//int d = 1/0;
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
	public String editItemSubmit(@Validated @ModelAttribute(value="item")ItemsCustom item,BindingResult bindingResult,Model model,MultipartFile pictureFile) throws Exception {
		//进行图片上传
		if (pictureFile!=null&&pictureFile.getOriginalFilename()!=null&&pictureFile.getOriginalFilename().length()>0) {
			//图片上传成功后，将图片地址写到数据库
			String filePath = "D:\\AliWorkbenchData\\";
			//文件名
			String originalFilename = pictureFile.getOriginalFilename();
			//文件新名字
			String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
			//新文件
			File file = new File(filePath+newFileName);
			//将内存中的文件写入磁盘
			pictureFile.transferTo(file);
			//图片上传成功，将新图片地址写入数据库
			item.setPic(newFileName);
		}
		//如果参数绑定时有错，输出校验错误信息
		if (bindingResult.hasErrors()) {
			//获取错误
			List<ObjectError> errors = bindingResult.getAllErrors();
			//准备在页面输出
			model.addAttribute("errors", errors);
			for (ObjectError objectError : errors) {
				System.out.println(objectError.getDefaultMessage());
			}
			return "editItem";
		}
		// 调用service更新商品信息
		itemsService.updateItems(item);
		// 重定向
		return "redirect:queryItems.action";
		// 转发
		// return "forward:queryItems.action";
//		return "editItem";
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
	
	//删除商品
	@RequestMapping("/deleteItem")
	public String deleteItem(Integer[] delete_id) throws Exception{
		return "success";
	}

	// 自定义属性编辑器
//	@InitBinder
//	public void initBinder(WebDataBinder binder) throws Exception {
//		// Date.class必须与controller方法形参pojo属性一致的date类型，这里是java.util.Date
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
//	}
	@RequestMapping("/viewItem/{id}")
	public @ResponseBody ItemsCustom viewItem(@PathVariable("id") Integer id) throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
}
