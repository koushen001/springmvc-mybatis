package com.cike.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cike.ssm.exception.CustomException;
import com.cike.ssm.mapper.ItemMapperCustom;
import com.cike.ssm.mapper.ItemsMapper;
import com.cike.ssm.po.Items;
import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;
import com.cike.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	// 注入mapper
	@Autowired
	private ItemMapperCustom itemMapperCustom;
	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if (items==null) {
			throw new CustomException("修改商品信息不存在！");
		}
		ItemsCustom itemsCustom = new ItemsCustom();
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItems(ItemsCustom itemsCustom) throws Exception {
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
