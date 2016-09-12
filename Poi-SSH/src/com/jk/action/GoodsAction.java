package com.jk.action;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jk.model.Goods;
import com.jk.service.GoodsService;
import com.jk.util.BaseAction;
import com.jk.util.FilesUtil;
public class GoodsAction extends BaseAction{
	private GoodsService goodsService;
	private FilesUtil filesUtil = new FilesUtil();
	private Goods goods = new Goods();
	public void setFilesUtil(FilesUtil filesUtil) {
		this.filesUtil = filesUtil;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public FilesUtil getFilesUtil() {
		return filesUtil;
	}
	public Goods getGoods() {
		return goods;
	}
	public void uploadGoodsInf() {
		String fileName = filesUtil.getTheFileFileName();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (fileType.equals(".xls")) {
			
			List<Goods> goodsList = new ArrayList<>();
			FileInputStream fis=null;
			try {
				fis=new FileInputStream(filesUtil.getTheFile());
				HSSFWorkbook workBook = new HSSFWorkbook(fis);
				//获取excel表中下标为1的第一张表的内容
				HSSFSheet sheet = workBook.getSheetAt(0);
				//获取最大行下标
				int maxRow=sheet.getLastRowNum();
				//循环获取行级元素,i小于等于最大的下标，等于是获取最后一行
				for (int i = 1; i <= maxRow; i++) {
					HSSFRow row = sheet.getRow(i);
					Goods goods = new Goods();
	
					goods.setGoodsNum(row.getCell(0).getStringCellValue());
					goods.setGoodsName(row.getCell(1).getStringCellValue());
					goods.setTypeName(row.getCell(2).getStringCellValue());
					goods.setCreateTime(row.getCell(3).getDateCellValue());
					goodsList.add(goods);
				}
				goodsService.addWuLiuData(goodsList);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
					try {
						if(fis!=null){
							fis.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	
	public void downLoadGoodsInf() {
		List<Goods> goodsList = (List<Goods>) goodsService.getGoodsList();
		//创建一个workbook 对应一个excel
		HSSFWorkbook workBook = new HSSFWorkbook();
		//在workbook里创建一个excel里的sheet
		HSSFSheet sheet = workBook.createSheet("goodsInf");

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		
		//在这个表里创建第一行
		HSSFRow row = sheet.createRow(0);
		//创建第一个单元格
		HSSFCell cell1 = row.createCell(0);
		cell1.setCellValue("goodsNum");
		HSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("goodsName");
		HSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("TypeName");
		HSSFCell cell4 = row.createCell(3);
		cell4.setCellValue("createTime");
		//从数据库查询到列表信息
		for (int i = 0; i < goodsList.size(); i++) {
			//根据列表长度创建excel的行 i+1 是因为i是表头
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(goodsList.get(i).getGoodsNum());
			row.createCell(1).setCellValue(goodsList.get(i).getGoodsName());
			row.createCell(2).setCellValue(goodsList.get(i).getTypeName());
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			String createTime = simple.format(goodsList.get(i).getCreateTime());
			row.createCell(3).setCellValue(createTime);
		}
		//将excel输出
		downloadXLSFile(getRequest(), getResponse(), workBook, "goodsInf.xls");
	}
	public static void downloadXLSFile(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
	        os = response.getOutputStream(); //重点突出(特别注意),通过response获取的输出流，作为服务端往客户端浏览器输出内容的一个通道
	        bos = new BufferedOutputStream(os);
	        // 处理下载文件名的乱码问题(根据浏览器的不同进行处理)
	        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	        	fileName = new String(fileName.getBytes("GB2312"),"ISO-8859-1");
	        } else { 
	        	// 对文件名进行编码处理中文问题
	  	        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
	  	        fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        }
	        response.reset(); // 重点突出
	        response.setCharacterEncoding("UTF-8"); // 重点突出
	        response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	      //  response.setHeader("Content-Disposition", "attachment; filename="+fileName); // 重点突出
	        workbook.write(bos);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			// 特别重要
	        // 1. 进行关闭是为了释放资源
	        // 2. 进行关闭会自动执行flush方法清空缓冲区内容
			try {
				if (null != bos) {
					bos.close();
					bos = null;
				}
				if (null != os) {
					os.close();
					os = null;
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
}
