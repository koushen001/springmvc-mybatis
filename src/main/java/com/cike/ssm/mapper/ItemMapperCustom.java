package com.cike.ssm.mapper;

import java.util.List;

import com.cike.ssm.po.ItemsCustom;
import com.cike.ssm.po.ItemsQueryVo;

public interface ItemMapperCustom {
	//商品查询列表
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
