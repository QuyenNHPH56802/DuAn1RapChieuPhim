/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.project.service.Responsitory_HoaDon;
import com.project.service.Respository_Payment;
import com.project.utils.DBConnect;
import com.sun.jna.platform.win32.Sspi;
import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;

/**
 *
 * @author admin
 */
public class Export_Bill {

    private Responsitory_HoaDon rp1 = new Responsitory_HoaDon();
    private Respository_Payment rp = new Respository_Payment();

    public String createQR(String fillPath, String userNamBank, double money) throws WriterException, IOException {
        String contentQr = new String(fillPath.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
//                "BANK://PAY?account=" + userNamBank + "&amount=" + money;
        String qrFilePath = fillPath + "/qrcode.png";
        QRCodeWriter qrCodeWrite = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWrite.encode(contentQr, BarcodeFormat.QR_CODE, 200, 200);
        Path path = (Path) FileSystems.getDefault().getPath(qrFilePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return qrFilePath;
    }

    public ModelHoaDon getID(int id) {
        ModelHoaDon lists = null;
        String sql = """
              SELECT [id]
                     ,[movie_name]
                     ,[room]
                     ,[booking_date]
                     ,[booking_time]
                     ,[seat_ids]
                     ,[member_id]
                     ,[quantity]
                     ,[total]
                     ,[status]
                     ,[voucher_id]
                     ,[time_at]
                     ,[combo_food]
                           ,[quality_food]
                 FROM [dbo].[tbl_bookings] where member_id = ?
              """;
        SimpleDateFormat fontTime = new SimpleDateFormat("HH:mm:ss");
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
           
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp showDate = rs.getTimestamp("time_at");
                java.sql.Timestamp showTime = rs.getTimestamp("booking_time");
                String format1 = fontTime.format(showTime);
                String format = fontTime.format(showDate);
                lists = new ModelHoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), format1, rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getString(10), rs.getInt(11),format,rs.getString(13),rs.getInt(14));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public Model_Payment getIok(int id) {
        Model_Payment diemdanh = null;
        String sql = """
              SELECT [id]
                    ,[booking_id]
                    ,[amount]
                    ,[payment_method]
                    ,[payment_status]
                    ,[payment_date]
                FROM [dbo].[tbl_payments] where booking_id = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                diemdanh = new Model_Payment(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diemdanh;
    }

    private String createNamePath(String selected, String userName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = sdf.format(new Date());
        return selected + "/hoa_don_" + userName.replaceAll(" ", "_") + "_" + time + ".pdf";
    }

    public void createBill(String fillPath, String tenKhachHang, List<Model_Payment> bill, String taiKhoanNganHang, int id,double total,String method) throws IOException, WriterException, DocumentException {
        String pdfFilePath = createNamePath(fillPath, tenKhachHang);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        document.open();
//        ModelHoaDon model = rp1.getIDCan(id);
        // Font cho tieu de
        BaseFont bf = BaseFont.createFont("src/main/resources/fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(bf, 20, Font.BOLD);
        Font regularFont = new Font(bf, 12, Font.NORMAL);

//        Font tittleFont = new Font(Font.FontFamily.HELVETICA,20,Font.BOLD);
        // Them tieu de cho hoa don 
        Paragraph tittle = new Paragraph("Hóa Đơn Bán Hàng", titleFont);
        tittle.setAlignment(Element.ALIGN_CENTER);
        document.add(tittle);
        document.add(new Paragraph("Khách Hàng :" + tenKhachHang, regularFont));
//         document.add(new Paragraph("Mã thanh toán: #" + model,regularFont ));
        document.add(new Paragraph("\n"));

        // tao bang cho ds san pham
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f); // Khoảng cách trước bảng
        table.setSpacingAfter(10f); // Khoảng cách sau bảng
//        table.setWidths(new float[]{2, 2, 2, 3  });
        ModelHoaDon model = getID(id);
        int maThanhToan = model.getId();
        String tenPhim = model.getMovieName();
        String room = model.getRoom();
        String time = model.getTime();
        int voucher = (int) model.getVoucherID();
        String nameCombo = model.getNameCombo();
        int soLuongComBo = model.getSoLuongCombo();
        int soLuongGhe = model.getQuaility();
        String ngayDat = model.getThoiGianThanhToan();
        String gioDat = model.getGioThanhToan();
        String ghe = model.getSeats_id();
        
//        document.add(new Paragraph("Mã thanh toán: " + maThanhToan ,regularFont));
//         document.add(new Paragraph("Tên phim: "+ tenPhim ,regularFont));
//        document.add(new Paragraph("Phòng chiếu: " + room,regularFont));
//           document.add(new Paragraph("Ngày đặt: " + ngayDat ,regularFont));
//                document.add(new Paragraph("Giờ đặt: " +gioDat ,regularFont));
//                document.add(new Paragraph("Ghế: " + ghe,regularFont));
//        document.add(new Paragraph(" "));
//        table.addCell(new Paragraph("Số Lượng Ghế", regularFont));
//        table.addCell(new Paragraph("Payment Method", regularFont));
//        table.addCell(new Paragraph("Payment Status", regularFont));
//        table.addCell(new Paragraph("Payment Date", regularFont));
        // thêm cac thanh phan vao bang và tinh tong tien
//           document.add(new Paragraph("Mã thanh toán: #" ));
//                document.add(new Paragraph("Mã đặt vé: #" ));
//                document.add(new Paragraph("Tên phim: " ));
//                document.add(new Paragraph("Phòng chiếu: " ));
//                document.add(new Paragraph(" "));
//                
//                PdfPTable table1 = new PdfPTable(2); // Bảng với 2 cột
//                PdfPCell cell;
//                table1.setWidthPercentage(100);
//        table1.setWidths(new float[]{ 2, 2});
//                cell = new PdfPCell(new Paragraph("Số tiền"));
//                table1.addCell(cell);
//                cell = new PdfPCell(new Paragraph("Phương thức thanh toán"));
//                table1.addCell(cell);
//                cell = new PdfPCell(new Paragraph("Trạng thái thanh toán"));
//                table1.addCell(cell);
//                cell = new PdfPCell(new Paragraph("Ngày thanh toán"));
//                table1.addCell(cell);
//                
       
        PdfPCell cell;
        cell = new PdfPCell(new Paragraph("Mã thanh toán:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER); // Tắt đường viền của ô
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.valueOf(maThanhToan), regularFont)); // Dữ liệu mẫu
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Tên phim:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(tenPhim, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Phòng chiếu:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(room, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Ngày đặt:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(ngayDat, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("Giờ đặt:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
        cell = new PdfPCell(new Paragraph(time, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("Giờ Chiếu:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(gioDat, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Ghế:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(ghe, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Số Lượng Ghế:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.valueOf(soLuongGhe), regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("Combo Name:", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(nameCombo, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
         cell = new PdfPCell(new Paragraph("Số lượng Combo đặt :", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(String.valueOf(soLuongComBo), regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
        cell = new PdfPCell(new Paragraph("Payment Method", regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph(method, regularFont));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
      
        
//        PdfPTable table1 = new PdfPTable(3);
//        table1.setWidthPercentage(100);
//        table1.addCell(new Paragraph("Payment Method", regularFont));
//        table1.addCell(new Paragraph("Payment Status", regularFont));
//        table1.addCell(new Paragraph("Payment Date", regularFont));

//        for (Model_Payment x : bill) {
////           table.addCell(String.valueOf(x.getId()));
////           table.addCell(String.valueOf(x.getBooking_id()));
////           table.addCell(String.format(soLuongGhe ));
//           table1.addCell(String.format(x.getPayment_method()));
//           table1.addCell(String.format(x.getPayment_status()));
//           table1.addCell(String.format(x.getPayment_date()));
//           
//        }
//        if (bill != null && !bill.isEmpty()) {
//            // Duyệt qua danh sách bill và thêm các giá trị vào bảng
//            for (Model_Payment x : bill) {
//                // Thêm phương thức thanh toán
//                table.addCell(new PdfPCell(new Paragraph(x.getPayment_method(), regularFont)));
//
//                // Thêm trạng thái thanh toán
//                table.addCell(new PdfPCell(new Paragraph(x.getPayment_status(), regularFont)));
//
//                // Thêm ngày thanh toán
//                table.addCell(new PdfPCell(new Paragraph(x.getPayment_date(), regularFont)));
//            }
//        } else {
//            // Nếu bill rỗng, bạn có thể thêm một thông báo cho người dùng hoặc ghi vào PDF
//            table.addCell(new PdfPCell(new Paragraph("No payment records found.", regularFont)));
//            table.addCell(new PdfPCell(new Paragraph("No payment records found.", regularFont)));
//            table.addCell(new PdfPCell(new Paragraph("No payment records found.", regularFont)));
//        }
        // Them tong tien vao bang 
        document.add(table);
        DecimalFormat df = new DecimalFormat("#,###");
        String okBro = df.format(total);
        document.add(new Paragraph("Tổng Tiền :    " + okBro + " VND", regularFont));
        if(voucher>0){
             cell = new PdfPCell(new Paragraph("Đã thêm voucher", regularFont));
             cell.setBorder(PdfPCell.NO_BORDER);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
        } else{
            cell = new PdfPCell(new Paragraph("Khong co voucher", regularFont));
             cell.setBorder(PdfPCell.NO_BORDER);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
        }

        String qrFilePath = createQR(fillPath, tenKhachHang, total);
        Image qrcoImage = Image.getInstance(qrFilePath);
        qrcoImage.setAlignment(Element.ALIGN_CENTER);
        document.add(new Paragraph("Quét mã QR để thanh toán :"));
        document.add(qrcoImage);
        document.close();
        new File(qrFilePath).delete();
    }

    public void inHoaDon(String fillPath) {
        try {
            File file = new File(fillPath);
            if (file.exists()) {
                Desktop.getDesktop().print(file);
            } else {
                JOptionPane.showMessageDialog(null, "Không thanh toán được !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
