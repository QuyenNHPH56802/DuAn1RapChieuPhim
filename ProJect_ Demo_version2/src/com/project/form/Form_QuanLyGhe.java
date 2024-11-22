
package com.project.form;

import com.project.model.ModelGhe;
import com.project.service.Respository_Ghe;
import com.project.swing.scrollbar.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Form_QuanLyGhe extends javax.swing.JPanel {
private DefaultTableModel model = new DefaultTableModel();
private Respository_Ghe rp = new Respository_Ghe();
private int index =  -1;
   
    public Form_QuanLyGhe() {
        initComponents();
        
                Font arialFont = new Font("Arial", Font.PLAIN, 14);
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
        
        txt_id.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_id.setBackground(Color.white);
        txt_id.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_id.setPreferredSize(new Dimension(250, 30));
        
        txt_lichChieu.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_lichChieu.setBackground(Color.white);
        txt_lichChieu.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_lichChieu.setPreferredSize(new Dimension(250, 30));
        
        txt_soGhe.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_soGhe.setBackground(Color.white);
        txt_soGhe.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_soGhe.setPreferredSize(new Dimension(250, 30));
        
        txt_maKhachHang.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_maKhachHang.setBackground(Color.white);
        txt_maKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_maKhachHang.setPreferredSize(new Dimension(250, 30));
        
         txt_trangThai.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_trangThai.setBackground(Color.white);
        txt_trangThai.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_trangThai.setPreferredSize(new Dimension(250, 30));
        
         txt_thoiGian.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_thoiGian.setBackground(Color.white);
        txt_thoiGian.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_thoiGian.setPreferredSize(new Dimension(250, 30));
        
         txt_loaiGhe.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_loaiGhe.setBackground(Color.white);
        txt_loaiGhe.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_loaiGhe.setPreferredSize(new Dimension(250, 30));
        
        txt_giaVe.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_loaiGhe.setBackground(Color.white);
        txt_loaiGhe.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_loaiGhe.setPreferredSize(new Dimension(250, 30));
        
        
        setOpaque(false);
        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        if(rp.getAll().isEmpty()){
            JOptionPane.showMessageDialog(null, "No data");
        }else{
            this.showData(index -1);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER);
        for (int i = 0; i < tbl_caHoc.getColumnCount(); i++) {
           tbl_caHoc.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
    public void fillTable(ArrayList<ModelGhe> lists){
        model = (DefaultTableModel)tbl_caHoc.getModel();
        model.setRowCount(0);
        for (ModelGhe x : lists) {
            model.addRow(x.toDataRow());
        }
    }
    public void showData(int index){
        String id = tbl_caHoc.getValueAt(index, 0).toString();
        String caHoc = tbl_caHoc.getValueAt(index, 1).toString();
        String batDau = tbl_caHoc.getValueAt(index, 2).toString();
        String ketThuc = tbl_caHoc.getValueAt(index, 3).toString();
        Object value = tbl_caHoc.getValueAt(index, 5);
        String khachHang = (value != null)? value.toString():"";
        String thoiGian = tbl_caHoc.getValueAt(index, 4).toString();
        String loaiGhe = tbl_caHoc.getValueAt(index, 6).toString();
        String giaVe = tbl_caHoc.getValueAt(index, 7).toString();
        
        txt_id.setText(id);
        txt_lichChieu.setText(caHoc);
        txt_soGhe.setText(batDau);
        txt_maKhachHang.setText(ketThuc);
        txt_trangThai.setText(thoiGian);
        txt_thoiGian.setText(khachHang);
        txt_loaiGhe.setText(loaiGhe);
        txt_giaVe.setText(giaVe);
        
    }
    ModelGhe readForm(){
       int id = Integer.parseInt(txt_id.getText().trim());
        String lichChieu = txt_lichChieu.getText().trim();
        String soGhe = txt_soGhe.getText().trim();
        int maKH = Integer.parseInt(txt_maKhachHang.getText().trim());
        String trangThai = txt_trangThai.getText().trim();
        String thoiGian = txt_thoiGian.getText().trim();
        String loaiGhe = txt_loaiGhe.getText().trim();
        String giaVe = txt_giaVe.getText().trim();
       if(txt_id.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Khong co id");
           txt_id.requestFocus();
           return null;
       }
       
        if(lichChieu.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lịch Chiếu khong co du lieu");
            txt_lichChieu.requestFocus();
            return null;
        }
        if (soGhe.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Số Ghế không có dữ liệu");
        txt_soGhe.requestFocus();
        return null; 
    }
         if (txt_maKhachHang.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Mã Khách Hàng không có dữ liệu");
        txt_maKhachHang.requestFocus();
        return null; 
    }
       if (trangThai.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Trạng thái không có dữ liệu");
        txt_trangThai.requestFocus();
        return null; 
    }  
        
    if (thoiGian.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Thời Gian không có dữ liệu");
        txt_thoiGian.requestFocus();
        return null; 
    } 
    if (loaiGhe.isEmpty()) {
        JOptionPane.showMessageDialog(null, " Loại Ghế không có dữ liệu");
        txt_loaiGhe.requestFocus();
        return null; 
    } 
     if (giaVe.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Giá Vé không có dữ liệu");
        txt_soGhe.requestFocus();
        return null; 
    }else {
        try {
           int giaVe1= Integer.parseInt(txt_giaVe.getText().trim()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giá Vé phải là số!");
            txt_giaVe.requestFocus();
            return null; 
        }
    }
    return new ModelGhe(id, id, soGhe, maKH, trangThai, thoiGian, loaiGhe, id);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tbl_caHoc = new com.project.table.Table();
        btn_Them = new com.raven.swing.Button();
        btn_sua = new com.raven.swing.Button();
        btn_xoa = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        txt_lichChieu = new javax.swing.JTextField();
        txt_soGhe = new javax.swing.JTextField();
        txt_maKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_trangThai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_giaVe = new javax.swing.JTextField();
        txt_loaiGhe = new javax.swing.JTextField();
        txt_thoiGian = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data Ghế");

        tbl_caHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lịch Chiếu ID", "Số Ghế ", "Mã Khách Hàng", "Trạng Thái", "Reseved Until", "Loại Ghế", "Giá Vé"
            }
        ));
        tbl_caHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_caHocMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_caHoc);

        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus (2).png"))); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
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
                .addGap(279, 279, 279)
                .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        jLabel2.setText("ID :");

        jLabel3.setText("Lịch Chiếu ID :");

        jLabel4.setText("Số ghế :");

        jLabel5.setText("Mã Khách Hàng :");

        jLabel6.setText("Trạng Thái :");

        jLabel7.setText("Thời Gian :");

        jLabel8.setText("Loại Ghế :");

        jLabel9.setText("Giá Vé :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_soGhe)
                    .addComponent(txt_id)
                    .addComponent(txt_lichChieu)
                    .addComponent(txt_maKhachHang))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(177, 177, 177)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_trangThai)
                    .addComponent(txt_thoiGian)
                    .addComponent(txt_giaVe)
                    .addComponent(txt_loaiGhe))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))
                            .addComponent(txt_lichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4))
                            .addComponent(txt_soGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6))
                            .addComponent(txt_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7))
                            .addComponent(txt_thoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_loaiGhe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9))
                            .addComponent(txt_giaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_caHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_caHocMouseClicked
        index = tbl_caHoc.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_caHocMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
         String id = txt_search.getText().toLowerCase();
        if (rp.timMa(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập loại ghế để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim Thay ghế");
            fillTable(rp.timMa(id));
        }

    }//GEN-LAST:event_btn_searchActionPerformed

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
        index = tbl_caHoc.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            int id = rp.getAll().get(index).getKhach_hang_id();
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
        index = tbl_caHoc.getSelectedRow();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Them;
    private com.raven.swing.Button btn_search;
    private com.raven.swing.Button btn_sua;
    private com.raven.swing.Button btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_caHoc;
    private javax.swing.JTextField txt_giaVe;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_lichChieu;
    private javax.swing.JTextField txt_loaiGhe;
    private javax.swing.JTextField txt_maKhachHang;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_soGhe;
    private javax.swing.JTextField txt_thoiGian;
    private javax.swing.JTextField txt_trangThai;
    // End of variables declaration//GEN-END:variables
}
