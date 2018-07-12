package com.yk.search_car.export;

import com.yk.search_car.model.used_house.UsedHouseModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//下面是和数据导出有关的包


/**
 * Created by YK on 2017/2/28.
 */
public class ExportExcel {


    public int Export(List<UsedHouseModel> list,String url){
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("房源表");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short)15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        //第一行第二个单元格
        cell = row.createCell( (short) 1);
        cell.setCellValue("title");
        CellRangeAddress region=new CellRangeAddress(0, 0, (short)1,(short) 3);//合并三个
        sheet.addMergedRegion(region);
        cell.setCellStyle(style);
        //第一行第三个单元格
        cell = row.createCell((short) 4);
        cell.setCellValue("houseName");
        cell.setCellStyle(style);
        //第一行第四个单元格
        cell = row.createCell((short) 5);
        cell.setCellValue("address");
        CellRangeAddress region01=new CellRangeAddress(0, 0, (short)5,(short) 6);//合并两个
        sheet.addMergedRegion(region01);
        cell.setCellStyle(style);
        //第一行第五个单元格
        cell = row.createCell((short) 7);
        cell.setCellValue("houseType");
        cell.setCellStyle(style);
        //第一行第六个单元格
        cell = row.createCell((short) 8);
        cell.setCellValue("area");
        cell.setCellStyle(style);
        //第一行第七个单元格
        cell = row.createCell((short) 9);
        cell.setCellValue("unitPrice");
        cell.setCellStyle(style);


        //向单元格里填充数据
        for (short i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);//循环一次，创建一行
            row.createCell(0).setCellValue(list.get(i).getId());//当前行，第一个格子填充内容
            row.createCell(1).setCellValue(list.get(i).getTitle());//当前行，第二个格子填充内容
            row.createCell(4).setCellValue(list.get(i).getHouseName());//合并了单元各所以没有2、3
            row.createCell(5).setCellValue(list.get(i).getAddress());
            row.createCell(7).setCellValue(list.get(i).getHouseType());
            row.createCell(8).setCellValue(list.get(i).getArea());
            row.createCell(9).setCellValue(list.get(i).getUnitPrice());
        }

        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream(url);
            wb.write(out);
            out.close();
            return 1;//返回1，表示操作成功
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;//返回2表示操作失败
    }

}
