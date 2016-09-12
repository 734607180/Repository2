package com.jk.dao;

import java.util.List;

import com.jk.model.Goods;

public interface GoodsDao {

	void addWuLiuData(List<Goods> goodsList);

	List<Goods> getGoodsList();

}
