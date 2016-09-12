package com.jk.model;

import java.util.Date;

public class Goods {
	private int goodsId;
	private String goodsNum;
	private String goodsName;
	private String TypeName;
	private Date createTime;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goodsId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goods other = (Goods) obj;
		if (goodsId != other.goodsId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsNum=" + goodsNum + ", goodsName=" + goodsName + ", TypeName="
				+ TypeName + ", createTime=" + createTime + "]";
	}
	public Goods() {
		super();
	}
	public Goods(int goodsId, String goodsNum, String goodsName, String typeName, Date createTime) {
		super();
		this.goodsId = goodsId;
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		TypeName = typeName;
		this.createTime = createTime;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}	
