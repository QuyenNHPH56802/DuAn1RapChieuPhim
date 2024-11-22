/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.model.ExportExcel_HoaDon;
import com.project.model.Export_Bill;
import com.project.model.ModelHoaDon;
import com.project.model.SaveLocation;
import com.project.service.Responsitory_HoaDon;
import com.project.swing.scrollbar.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import com.project.swing.CustomTextField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author admin
 */
public class Form_HoaDon extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private Responsitory_HoaDon rp = new Responsitory_HoaDon();
    private int index = -1;
    private ExportExcel_HoaDon excel;
    private int currentIndex = 0; 
    private ArrayList<ModelHoaDon> hoaDons;
    private static final int RECORDS_PER_PAGE = 8; 



    /**
     * Creates new form Form_DiemDanh
     */
    public Form_HoaDon() {
        initComponents();
        
        
        setOpaque(false);
        
        Font arialFont = new Font("Arial", Font.PLAIN, 14);
        jLabel1.setFont(arialFont);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setFont(arialFont);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setFont(arialFont);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setFont(arialFont);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setFont(arialFont);
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setFont(arialFont);
        jLabel6.setForeground(Color.WHITE);
        jLabel7.setFont(arialFont);
        jLabel7.setForeground(Color.WHITE);
        jLabel8.setFont(arialFont);
        jLabel8.setForeground(Color.WHITE);
        jLabel9.setFont(arialFont);
        jLabel9.setForeground(Color.WHITE);
        jLabel10.setFont(arialFont);
        jLabel10.setForeground(Color.WHITE);
        jLabel11.setFont(arialFont);
        jLabel11.setForeground(Color.WHITE);
        jLabel12.setFont(arialFont);
        jLabel12.setForeground(Color.WHITE);

        

txt_ma.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_ma.setBackground(Color.white);
txt_ma.setFont(new Font("Arial", Font.PLAIN, 14));
txt_ma.setPreferredSize(new Dimension(250, 30)); 


txt_Phong.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_Phong.setBackground(Color.white);
txt_Phong.setFont(new Font("Arial", Font.PLAIN, 14));
txt_Phong.setPreferredSize(new Dimension(250, 30)); 


txt_tenPhim.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_tenPhim.setBackground(Color.white);
txt_tenPhim.setFont(new Font("Arial", Font.PLAIN, 14));
txt_tenPhim.setPreferredSize(new Dimension(250, 30)); 


txt_NgayBatDau.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_NgayBatDau.setBackground(Color.white);
txt_NgayBatDau.setFont(new Font("Arial", Font.PLAIN, 14));
txt_NgayBatDau.setPreferredSize(new Dimension(250, 30)); 


txt_thoiGian.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_thoiGian.setBackground(Color.white);
txt_thoiGian.setFont(new Font("Arial", Font.PLAIN, 14));
txt_thoiGian.setPreferredSize(new Dimension(250, 30));

txt_idghe.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_idghe.setBackground(Color.white);
txt_idghe.setFont(new Font("Arial", Font.PLAIN, 14));
txt_idghe.setPreferredSize(new Dimension(250, 30));

txt_khachHang.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_khachHang.setBackground(Color.white);
txt_khachHang.setFont(new Font("Arial", Font.PLAIN, 14));
txt_khachHang.setPreferredSize(new Dimension(250, 30));

txt_sl.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_sl.setBackground(Color.white);
txt_sl.setFont(new Font("Arial", Font.PLAIN, 14));
txt_sl.setPreferredSize(new Dimension(250, 30));

txt_trangThai.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_trangThai.setBackground(Color.white);
txt_trangThai.setFont(new Font("Arial", Font.PLAIN, 14));
txt_trangThai.setPreferredSize(new Dimension(250, 30));

txt_voucher.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_voucher.setBackground(Color.white);
txt_voucher.setFont(new Font("Arial", Font.PLAIN, 14));
txt_voucher.setPreferredSize(new Dimension(250, 30));

txt_tong.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_tong.setBackground(Color.white);
txt_tong.setFont(new Font("Arial", Font.PLAIN, 14));
txt_tong.setPreferredSize(new Dimension(250, 30));

        tbl_diemDanh.setPreferredScrollableViewportSize(new Dimension(500, 200));
        tbl_diemDanh.setFillsViewportHeight(true);

        // Tải tất cả bản ghi
        hoaDons = rp.getAll();
        loadMoreRecords(); // Tải 8 bản ghi đầu tiên

        // Thiết lập nút điều khiển
        loadMoreButton.addActionListener(e -> loadMoreRecords());
        loadPreviousButton.addActionListener(e -> loadPreviousRecords());

        // Kích hoạt hoặc vô hiệu hóa nút ban đầu
        loadPreviousButton.setEnabled(false);
        loadMoreButton.setEnabled(true);
    
        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        if (rp.getAll().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data");
        } else {
            this.showData(index - 1);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER);
        for (int i = 0; i < tbl_diemDanh.getColumnCount(); i++) {
            tbl_diemDanh.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
//        TableColumnModel colum1 = tbl_diemDanh.getColumnModel();
//        TableColumn colum1_1 = colum1.getColumn(0);
//        colum1_1.setPreferredWidth(20);
//        TableColumn column2 = colum1.getColumn(1);
//        column2.setPreferredWidth(20);
        int[] columnWiths = {
            10, 10, 10
        };
        for (int i = 0; i < columnWiths.length; i++) {
            tbl_diemDanh.getColumnModel().getColumn(i).setPreferredWidth(columnWiths[i]);
        }
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        // Sử dụng ModernScrollBarUI hoặc giao diện thanh cuộn tùy chỉnh khác
        verticalScrollBar.setUI(new ModernScrollBarUI()); // Thay MetalScrollBarUI bằng ModernScrollBarUI nếu bạn có
        horizontalScrollBar.setUI(new ModernScrollBarUI()); // Cũng áp dụng cho thanh cuộn ngang nếu cần

        // Điều chỉnh kích thước của thanh cuộn
        verticalScrollBar.setPreferredSize(new Dimension(10, 0)); // Thanh cuộn dọc có chiều rộng là 10 pixel
        horizontalScrollBar.setPreferredSize(new Dimension(0, 10)); // Thanh cuộn ngang có chiều cao là 10 pixel

        // Thiết lập màu sắc cho thanh cuộn
        verticalScrollBar.setForeground(new Color(255, 255, 255, 80)); // Màu thanh cuộn có độ trong suốt
        horizontalScrollBar.setForeground(new Color(255, 255, 255, 80));

        // Cài đặt tốc độ cuộn
        verticalScrollBar.setUnitIncrement(20); // Tốc độ cuộn cho phím mũi tên dọc
        horizontalScrollBar.setUnitIncrement(20); // Tốc độ cuộn cho phím mũi tên ngang

        // Ẩn thanh cuộn ngang nếu không muốn hiển thị
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Ẩn thanh cuộn ngang
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Ẩn thanh cuộn dọc
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Không cuộn ngang
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Cuộn dọc
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Tăng tốc độ cuộn

      

        scrollPane.setOpaque(false); // Để ẩn nền của JScrollPane
        scrollPane.getViewport().setOpaque(false); // Để làm trong suốt Viewport

        // Làm thanh cuộn trong suốt (nếu muốn)
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        
        
    }

    public void fillTable(ArrayList<ModelHoaDon> lists) {
        model = (DefaultTableModel) tbl_diemDanh.getModel();
        model.setRowCount(0);
        for (ModelHoaDon x : lists) {
            model.addRow(x.toDataRow());
        }
    }

private void loadMoreRecords() {
        int totalRecords = hoaDons.size();
        // Tính chỉ số cuối để tải bản ghi
        int endIndex = Math.min(currentIndex + RECORDS_PER_PAGE, totalRecords);
        
        
        model.setRowCount(0); 

       
        for (int i = currentIndex; i < endIndex; i++) {
            ModelHoaDon hoaDon = hoaDons.get(i);
            model.addRow(hoaDon.toDataRow());
        }

        
        currentIndex += RECORDS_PER_PAGE;

        // Cập nhật trạng thái của nút
        loadPreviousButton.setEnabled(currentIndex > RECORDS_PER_PAGE);
        loadMoreButton.setEnabled(currentIndex < totalRecords);
    }

    private void loadPreviousRecords() {
        if (currentIndex > 0) {
            currentIndex -= RECORDS_PER_PAGE;
            if (currentIndex < 0) currentIndex = 0;
            fillTableWithCurrentRecords(); // Tải lại bản ghi
        }
    }

    private void fillTableWithCurrentRecords() {
        int totalRecords = hoaDons.size();
        // Xóa dữ liệu cũ trong model
        model.setRowCount(0); 

        // Tính chỉ số cuối để tải bản ghi
        int endIndex = Math.min(currentIndex + RECORDS_PER_PAGE, totalRecords);

        // Tải lại các bản ghi từ currentIndex đến endIndex
        for (int i = currentIndex; i < endIndex; i++) {
            ModelHoaDon hoaDon = hoaDons.get(i);
            model.addRow(hoaDon.toDataRow());
        }

        // Cập nhật trạng thái của nút
        loadPreviousButton.setEnabled(currentIndex > 0);
        loadMoreButton.setEnabled(currentIndex < totalRecords);
    }

    public void showData(int index) {
        String id = tbl_diemDanh.getValueAt(index, 0).toString();
        String tenPhim = tbl_diemDanh.getValueAt(index, 1).toString();
        String phong = tbl_diemDanh.getValueAt(index, 2).toString();
        String ngayBatDau = tbl_diemDanh.getValueAt(index, 3).toString();
        String thoiGian = tbl_diemDanh.getValueAt(index, 4).toString();
        String id_ghe = tbl_diemDanh.getValueAt(index, 5).toString();
        String id_khachHang = tbl_diemDanh.getValueAt(index, 6).toString();
        String soLuong = tbl_diemDanh.getValueAt(index,7).toString();
        String tong = tbl_diemDanh.getValueAt(index, 8).toString();
        String trangThai = tbl_diemDanh.getValueAt(index, 9).toString();
        String voucher = tbl_diemDanh.getValueAt(index, 10).toString();
        txt_ma.setText(id);
        txt_tenPhim.setText(tenPhim);
        txt_Phong.setText(phong);
        txt_NgayBatDau.setText(ngayBatDau);
        txt_thoiGian.setText(thoiGian);
        txt_khachHang.setText(id_khachHang);
        txt_idghe.setText(id_ghe);
        txt_sl.setText(soLuong);
        txt_tong.setText(tong);
        txt_trangThai.setText(trangThai);
        txt_voucher.setText(voucher);

    }
      ModelHoaDon readForm(){
       int id = Integer.parseInt(txt_ma.getText().trim());
        String tenPhim = txt_tenPhim.getText().trim();
        String room = txt_Phong.getText().trim();
        String ngayBat = txt_NgayBatDau.getText().trim();
        String thoiGian = txt_thoiGian.getText().trim();
        String idGhe = txt_idghe.getText().trim();
        int member_id = Integer.parseInt(txt_khachHang.getText().trim());
        int soLuong= Integer.valueOf(txt_sl.getText().trim());
        double tong = Double.parseDouble(txt_tong.getText().trim());
        String trangThai = txt_trangThai.getText().trim();
        int voucher = Integer.parseInt(txt_voucher.getText().trim());
       if(txt_ma.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Khong co id");
           txt_ma.requestFocus();
           return null;
       }
       
        if(tenPhim.isEmpty()){
            JOptionPane.showMessageDialog(null, " Tên Phim khong co du lieu");
            txt_tenPhim.requestFocus();
            return null;
        }
        if (room.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Phòng không có dữ liệu");
        txt_Phong.requestFocus();
        return null; 
    }
         if (ngayBat.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không có dữ liệu");
        txt_NgayBatDau.requestFocus();
        return null; 
    }
       if (thoiGian.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Thời gian không có dữ liệu");
        txt_thoiGian.requestFocus();
        return null; 
    }  
        
    if (idGhe.isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID_Ghe không có dữ liệu");
        txt_idghe.requestFocus();
        return null; 
    } else {
        try {
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id_ghe phải là số!");
            txt_idghe.requestFocus();
            return null; 
        }
    }
    if (txt_khachHang.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID Khách Hàng  không có dữ liệu");
        txt_khachHang.requestFocus();
        return null; 
    } else {
        try {
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID Khách Hàng phải là số!");
            txt_khachHang.requestFocus();
            return null; 
        }
    }
    if (txt_sl.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Số Lượng không có dữ liệu");
        txt_khachHang.requestFocus();
        return null; 
    } else {
        try {
            int sl = Integer.parseInt(txt_sl.getText().trim());
           if(sl < 0){
               JOptionPane.showMessageDialog(null, "Số Lượng nhỏ hơn 0");
           }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số Lượng phải là số!");
            txt_khachHang.requestFocus();
            return null; 
        }
    }
    if (txt_tong.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Tổng không có dữ liệu");
        txt_tong.requestFocus();
        return null; 
    } else {
        try {
            int sl1 = Integer.parseInt(txt_tong.getText().trim());
           if(sl1 < 0){
               JOptionPane.showMessageDialog(null, "Tổng nhỏ hơn 0");
           }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tổng phải là số!");
            txt_tong.requestFocus();
            return null; 
        }
    }
    if (trangThai.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Trạng Thái không có dữ liệu");
        txt_trangThai.requestFocus();
        return null; 
    }
    if (txt_voucher.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Voucher không có dữ liệu");
        txt_voucher.requestFocus();
        return null; 
    }
    return new ModelHoaDon(id, tenPhim, room,ngayBat, thoiGian, trangThai, member_id, soLuong, tong, trangThai,voucher);
    }
      
      
      
      
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tbl_diemDanh = new com.project.table.Table();
        btn_sua = new com.raven.swing.Button();
        btn_Them = new com.raven.swing.Button();
        btn_xoa = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        loadPreviousButton = new javax.swing.JButton();
        loadMoreButton = new javax.swing.JButton();
        txt_ma = new javax.swing.JTextField();
        txt_tenPhim = new javax.swing.JTextField();
        txt_Phong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_NgayBatDau = new javax.swing.JTextField();
        txt_thoiGian = new javax.swing.JTextField();
        txt_khachHang = new javax.swing.JTextField();
        txt_idghe = new javax.swing.JTextField();
        btn_hoaDon = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_sl = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_tong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_trangThai = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_voucher = new javax.swing.JTextField();

        setEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data Ca Học");

        tbl_diemDanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Phim", "Phòng", "Ngày Bắt Đầu", "Thời Gian Bắt  Đầu", "ID_Ghế", "ID_Khách Hàng", "Số Lượng Ghế", "Tổng", "Trạng Thái", "Voucher", "Combo Food", "Số Lượng Combo"
            }
        ));
        tbl_diemDanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_diemDanhMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_diemDanh);

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus (2).png"))); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/delete.png"))); // NOI18N
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_search.setBackground(new java.awt.Color(255, 102, 102));
        btn_search.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/magnifying-glass (2).png"))); // NOI18N
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        loadPreviousButton.setText("<");
        loadPreviousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPreviousButtonActionPerformed(evt);
            }
        });

        loadMoreButton.setText(">");
        loadMoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMoreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loadPreviousButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadMoreButton)
                .addGap(20, 20, 20)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(scrollPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadPreviousButton)
                        .addComponent(loadMoreButton))
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );

        txt_ma.setMaximumSize(new java.awt.Dimension(64, 22));

        txt_tenPhim.setMaximumSize(new java.awt.Dimension(64, 22));
        txt_tenPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenPhimActionPerformed(evt);
            }
        });

        txt_Phong.setMaximumSize(new java.awt.Dimension(64, 22));
        txt_Phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PhongActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N
        jLabel2.setText("ID :");

        jLabel3.setText("Tên Phim :");

        jLabel4.setText("Phòng :");

        jLabel5.setText("Thời Gian Bắt Đầu :");

        jLabel6.setText("ID_Ghế :");

        jLabel7.setText("ID_Khách Hàng :");

        jLabel8.setText("Ngày Bắt Đầu :");

        txt_NgayBatDau.setMaximumSize(new java.awt.Dimension(64, 22));
        txt_NgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayBatDauActionPerformed(evt);
            }
        });

        txt_thoiGian.setMaximumSize(new java.awt.Dimension(64, 22));

        txt_khachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_khachHangActionPerformed(evt);
            }
        });

        btn_hoaDon.setText("Hóa Đơn");
        btn_hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoaDonActionPerformed(evt);
            }
        });

        jLabel9.setText("Số Lượng :");

        txt_sl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_slActionPerformed(evt);
            }
        });

        jLabel10.setText("Tổng :");

        jLabel11.setText("Trạng Thái :");

        jLabel12.setText("Voucher :");

        txt_voucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_voucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hoaDon)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Phong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(txt_ma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_thoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_khachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(txt_idghe)
                    .addComponent(txt_sl)
                    .addComponent(txt_tong)
                    .addComponent(txt_trangThai)
                    .addComponent(txt_voucher))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btn_hoaDon))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txt_idghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_tenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_thoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txt_tong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txt_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_voucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
         int id = Integer.parseInt(txt_search.getText().trim());
        if (rp.timTen(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim Thay hoá dơn");
            fillTable(rp.timTen(id));
        }

    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoaDonActionPerformed
excel.exportCustomerData(rp.getAll());
        
        
    }//GEN-LAST:event_btn_hoaDonActionPerformed

    private void txt_NgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayBatDauActionPerformed

    private void txt_tenPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenPhimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenPhimActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        if(readForm()!= null){
            int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn chứ ?");
            if(chon ==0){
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được thêm !!!");
                rp.add(readForm());
                fillTable(rp.getAll());
            }else{
                JOptionPane.showMessageDialog(this, "Bạn không thêm dữ liệu !!!");
                fillTable(rp.getAll());
            }
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        index = tbl_diemDanh.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            int id = rp.getAll().get(index).getId();
            if(readForm()!= null){
                int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn chứ ?");
                if(chon ==0){
                    JOptionPane.showMessageDialog(this, "Dữ liệu đã được sửa !!!");
                    rp.update(id, readForm());
                    fillTable(rp.getAll());
                }else{
                    JOptionPane.showMessageDialog(this, "Bạn không sửa dữ liệu !!!");
                    fillTable(rp.getAll());
                }
            }

        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        index = tbl_diemDanh.getSelectedRow();
        if(index < 0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            int id = rp.getAll().get(index).getId();
            int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn chứ ?");
            if(chon ==0){
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được xoá !!!");
                rp.delete(id);
                fillTable(rp.getAll());
            }else{
                JOptionPane.showMessageDialog(this, "Bạn không xóa dữ liệu !!!");
                fillTable(rp.getAll());
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void txt_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PhongActionPerformed

    private void txt_khachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_khachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_khachHangActionPerformed

    private void txt_slActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_slActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_slActionPerformed

    private void txt_voucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_voucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_voucherActionPerformed

    private void loadPreviousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPreviousButtonActionPerformed
       loadPreviousButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadPreviousRecords();
    }
});
    }//GEN-LAST:event_loadPreviousButtonActionPerformed

    private void loadMoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMoreButtonActionPerformed
        loadMoreButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadMoreRecords();
    }
});
    }//GEN-LAST:event_loadMoreButtonActionPerformed

    private void tbl_diemDanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_diemDanhMouseClicked
        index = tbl_diemDanh.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_diemDanhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Them;
    private javax.swing.JButton btn_hoaDon;
    private com.raven.swing.Button btn_search;
    private com.raven.swing.Button btn_sua;
    private com.raven.swing.Button btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loadMoreButton;
    private javax.swing.JButton loadPreviousButton;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_diemDanh;
    private javax.swing.JTextField txt_NgayBatDau;
    private javax.swing.JTextField txt_Phong;
    private javax.swing.JTextField txt_idghe;
    private javax.swing.JTextField txt_khachHang;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_sl;
    private javax.swing.JTextField txt_tenPhim;
    private javax.swing.JTextField txt_thoiGian;
    private javax.swing.JTextField txt_tong;
    private javax.swing.JTextField txt_trangThai;
    private javax.swing.JTextField txt_voucher;
    // End of variables declaration//GEN-END:variables
}
