/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.examples.xssf.usermodel.CreateCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class ExportExcel_KhachHang {
    public static void exportCustomerData(List<ModelKhachHang> student){
        //tạo workbook và sheet
        Workbook workbook = new   XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Thống Kê Khách Hàng");
      Row mainTitleRow = sheet.createRow(0);
        Cell mainTitleCell = mainTitleRow.createCell(0);
        mainTitleCell.setCellValue("Thống Kê Khách Hàng");
        
        CellStyle mainTitleStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font mainTitleFont = workbook.createFont();
        mainTitleFont.setBold(true);
        mainTitleFont.setFontHeightInPoints((short) 14);
        mainTitleStyle.setFont(mainTitleFont);
        mainTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        mainTitleCell.setCellStyle(mainTitleStyle);
    int pixelWidth = 20;
    int poiWidth = (int)((pixelWidth * 256/7.0017));
    sheet.setColumnWidth(0, poiWidth);
    sheet.setColumnWidth(1, 5000);
        
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 7000);
       ;
    // tạo tiêu đề cột
    String[] header ={"ID ", "Tên ","SĐT",  "Gmail"};
        Row headerRow = sheet .createRow(1);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,header.length -1));
        for (int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
        }
          CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        int rowNum = 2;
        for (ModelKhachHang st : student) {
            Row row  = sheet.createRow(rowNum ++);
            Cell cell0 = row.createCell(0);
            row.createCell(0).setCellValue(st.getId());
            cell0.setCellStyle(centerStyle);
            row.createCell(1).setCellValue(st.getHoTen());
            row.createCell(2).setCellValue(st.getEmail());
            row.createCell(3).setCellValue(st.getSdt());
            
        }
//        CellStyle centerStyle = workbook.createCellStyle();
//centerStyle.setAlignment(HorizontalAlignment.CENTER);
//
//int rowNum = 1;
//for (ModelStudent st : student) {
//    Row row = sheet.createRow(rowNum++);
//    for (int i = 0; i < 6; i++) {
//        Cell cell = row.createCell(i);
//        switch (i) {
//            case 0: cell.setCellValue(st.getId()); break;
//            case 1: cell.setCellValue(st.getHoten()); break;
//            case 2: cell.setCellValue(st.getNamSinh()); break;
//            case 3: cell.setCellValue(st.getGmail()); break;
//            case 4: cell.setCellValue(st.getSdt()); break;
//            case 5: cell.setCellValue(st.getTrangThai()); break;
//        }
//        cell.setCellStyle(centerStyle);  // Áp dụng căn giữa cho tất cả ô
//    }
//}
        // hiển thị cửa sổ chọn lưu file
        JFileChooser jfile = new JFileChooser();
        jfile.setDialogTitle("Chọn vị trí lưu file Excel");
        int userSelected = jfile.showSaveDialog(null);
        if(userSelected == JFileChooser.APPROVE_OPTION){
            String filePath = jfile.getSelectedFile().getAbsolutePath();
            if(!filePath.endsWith(".xlsx")){
                filePath +=  ".xlsx";
            }
        
        // ghi workbook vào file
        try (FileOutputStream fileOut = new  FileOutputStream(filePath)){
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "File excel đã được tạo thành công :  " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
    
    
    }   
            
}
