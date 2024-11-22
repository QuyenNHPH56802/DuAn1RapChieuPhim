/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import java.awt.Font;
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
public class ExportExcel_HoaDon {

    public static void exportCustomerData(List<ModelHoaDon> student) {
        //tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Thống kê hóa đơn");
        Row mainTitleRow = sheet.createRow(0);
        Cell mainTitleCell = mainTitleRow.createCell(0);
        mainTitleCell.setCellValue("Thống kê hóa đơn");

        // Tạo style cho tiêu đề chính
        CellStyle mainTitleStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font mainTitleFont = workbook.createFont();
        mainTitleFont.setBold(true);
        mainTitleFont.setFontHeightInPoints((short) 14);
        mainTitleStyle.setFont(mainTitleFont);
        mainTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        mainTitleCell.setCellStyle(mainTitleStyle);

        int pixelWidth = 20;
        int poiWidth = (int) ((pixelWidth * 256 / 7.0017));
        sheet.setColumnWidth(0, poiWidth);
        sheet.setColumnWidth(1, 4000);

        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(5, 3000);  
        sheet.setColumnWidth(4, 3000);
         sheet.setColumnWidth(6, 4000);
          sheet.setColumnWidth(7, 4000);
           sheet.setColumnWidth(8, 4000);
            sheet.setColumnWidth(10, 10000);
             sheet.setColumnWidth(12,4000);
             sheet.setColumnWidth(11,4000);
        // tạo tiêu đề cột
        String[] header = {"ID ", "Tên Phim ", "Phòng", "Thời Gian Thanh Toán", "Giờ Thanh Toán", "ID_Ghế", "ID_Khách Hàng", "Số Lượng Ghế", "Trạng Thái", "Giờ Đặt", "Tên Combo", "Số Lượng Combo", "Tổng Tiền"};
        Row headerRow = sheet.createRow(1);
        // Hợp nhất các ô cho tiêu đề chính
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length - 1));
        for (int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
        }
        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        int rowNum = 2;
        for (ModelHoaDon st : student) {
            Row row = sheet.createRow(rowNum++);
            Cell cell0 = row.createCell(0);
            row.createCell(0).setCellValue(st.getId());
            cell0.setCellStyle(centerStyle);
            row.createCell(1).setCellValue(st.getMovieName());
            row.createCell(2).setCellValue(st.getRoom());
            row.createCell(3).setCellValue(st.getThoiGianThanhToan());
            row.createCell(4).setCellValue(st.getGioThanhToan());
            row.createCell(5).setCellValue(st.getSeats_id());
            row.createCell(6).setCellValue(st.getMemberID());
            row.createCell(7).setCellValue(st.getQuaility());
            
            row.createCell(8).setCellValue(st.getStatus());

            row.createCell(9).setCellValue(st.getTime());
            row.createCell(10).setCellValue(st.getNameCombo());
            row.createCell(11).setCellValue(st.getSoLuongCombo());
            row.createCell(12).setCellValue(st.getTotal());

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
        if (userSelected == JFileChooser.APPROVE_OPTION) {
            String filePath = jfile.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // ghi workbook vào file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "File excel đã được tạo thành công :  " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
