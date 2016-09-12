package com.jk.service;

import java.util.List;

import com.jk.model.Goods;

public interface GoodsService {

	void addWuLiuData(List<Goods> goodsList);

	List<Goods> getGoodsList();

}
