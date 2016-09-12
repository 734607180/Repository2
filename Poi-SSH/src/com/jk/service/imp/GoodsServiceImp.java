package com.jk.service.imp;

import java.util.List;

import com.jk.dao.GoodsDao;
import com.jk.model.Goods;
import com.jk.service.GoodsService;

public class GoodsServiceImp implements GoodsService{
	private GoodsDao goodsDao;

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Override
	public void addWuLiuData(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		goodsDao.addWuLiuData(goodsList);
	}

	@Override
	public List<Goods> getGoodsList() {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsList();
	}
}
