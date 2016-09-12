package com.jk.dao.imp;

import java.util.List;

import com.jk.dao.GoodsDao;
import com.jk.model.Goods;
import com.jk.util.HibernateUtil;

public class GoodsDaoImp extends HibernateUtil implements GoodsDao{

	@Override
	public void addWuLiuData(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);
			getHibernateTemplate().save(goods);
		}
	}

	@Override
	public List<Goods> getGoodsList() {
		// TODO Auto-generated method stub
		return (List<Goods>)getSessionBy().createQuery("from Goods").list();
	}

}
