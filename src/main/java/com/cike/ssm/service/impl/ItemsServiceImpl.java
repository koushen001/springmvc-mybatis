package com.cike.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cike.ssm.mapper.ItemMapperCustom;
import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;
import com.cike.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	// 注入mapper
	@Autowired
	private ItemMapperCustom itemMapperCustom;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemMapperCustom.findItemsList(itemsQueryVo);
	}

}
