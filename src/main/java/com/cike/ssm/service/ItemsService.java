package com.cike.ssm.service;

import java.util.List;

import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;

public interface ItemsService {
	// 商品查询列表
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	// 根据id查询商品
	public ItemsCustom findItemsById(int id) throws Exception;

	// 更新商品信息
	public void updateItems(ItemsCustom itemsCustom) throws Exception;
}
