/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.model.ModelListFilm;
import com.project.service.Responsitory_Students;
import com.project.swing.scrollbar.ModernScrollBarUI;
import com.project_pa.Header;
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

/**
 *
 * @author admin
 */
public class Form_Home extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private Responsitory_Students rp = new Responsitory_Students();
    private int index = -1;

    public Form_Home() {
        initComponents();
        setOpaque(false);
        
        txt_mota.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_mota.setBackground(new Color(255,255,255)); 
        txt_mota.setForeground(Color.BLACK);
        txt_mota.setLineWrap(true);
        txt_mota.setWrapStyleWord(true);
        
        txt_id.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_id.setBackground(Color.white);
txt_id.setFont(new Font("Arial", Font.PLAIN, 14));
txt_id.setPreferredSize(new Dimension(250, 30));


txt_hoten.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_hoten.setBackground(Color.white);
txt_hoten.setFont(new Font("Arial", Font.PLAIN, 14));
txt_hoten.setPreferredSize(new Dimension(250, 30));

txt_daoDien.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_daoDien.setBackground(Color.white);
txt_daoDien.setFont(new Font("Arial", Font.PLAIN, 14));
txt_daoDien.setPreferredSize(new Dimension(250, 30));

txt_theLoai.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_theLoai.setBackground(Color.white);
txt_theLoai.setFont(new Font("Arial", Font.PLAIN, 14));
txt_theLoai.setPreferredSize(new Dimension(250, 30));
Font arialFont = new Font("Arial", Font.PLAIN, 14);
        jLabel2.setFont(arialFont);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setFont(arialFont);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setFont(arialFont);
        jLabel4.setForeground(Color.WHITE);
        jLabel6.setFont(arialFont);
        jLabel6.setForeground(Color.WHITE);
        jLabel8.setFont(arialFont);
        jLabel8.setForeground(Color.WHITE);
    


        
        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        tbl_Students.getColumnModel().getColumn(0).setPreferredWidth(30);
        if (rp.getAll().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Khong co dl");
        } else {
            this.showData(index - 1);
        }

        // Tạo một renderer để căn giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER); // Căn giữa chữ

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < tbl_Students.getColumnCount(); i++) {
            tbl_Students.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
public void searchAndFillTable(String searchQuery) {
    ArrayList<ModelListFilm> searchResults = rp.timMa(searchQuery); // Giả sử `rp` có phương thức `searchById`

    if (searchResults.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy học sinh với mã: " + searchQuery);
    } else {
        fillTable(rp.timMa(searchQuery)); 
        // Gọi `fillTable` để hiển thị kết quả
    }
}

    public void fillTable(ArrayList<ModelListFilm> lists) {
        model = (DefaultTableModel) tbl_Students.getModel();
        model.setRowCount(0);
        for (ModelListFilm x : lists) {
            model.addRow(x.toDataRow());
        }
    }
    public void updateTable(ArrayList<ModelListFilm> students) {
    fillTable(students); // Làm mới bảng với dữ liệu mới
    tbl_Students.revalidate(); // Cập nhật bố cục
    tbl_Students.repaint(); // Vẽ lại bảng để phản ánh các thay đổi
}
    public void showData(int index) {
        String id = tbl_Students.getValueAt(index, 0).toString();
        String hoTen = tbl_Students.getValueAt(index, 1).toString();
        String namSinh = tbl_Students.getValueAt(index, 2).toString();
        String gmail = tbl_Students.getValueAt(index, 3).toString();
        String sdt = tbl_Students.getValueAt(index, 4).toString();
        
        txt_id.setText(id);
        txt_hoten.setText(hoTen);
        txt_daoDien.setText(gmail);
        txt_theLoai.setText(namSinh);
        txt_mota.setText(sdt);
        
        
    }
    ModelListFilm readForm(){
       int id = Integer.parseInt(txt_id.getText().trim());
        String hoten = txt_hoten.getText().trim();
        String namSinh = txt_daoDien.getText().trim();
        String gmail = txt_theLoai.getText().trim();
        String thoiGian = txt_mota.getText().trim();
        int sdt;
        String moTa = txt_mota.getText().trim();
       if(txt_id.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Khong co id");
           txt_id.requestFocus();
           return null;
       }
       
        if(hoten.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ho ten khong co du lieu");
            txt_hoten.requestFocus();
            return null;
        }
        if (namSinh.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Năm sinh không có dữ liệu");
        txt_daoDien.requestFocus();
        return null; 
    }
         if (gmail.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Gmail không có dữ liệu");
        txt_theLoai.requestFocus();
        return null; 
    }
       if (moTa.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Trạng thái không có dữ liệu");
        txt_mota.requestFocus();
        return null; 
    }  
        
    if (thoiGian.isEmpty()) {
        JOptionPane.showMessageDialog(null, "SDT không có dữ liệu");
        txt_mota.requestFocus();
        return null; 
    } else {
        try {
            sdt = Integer.parseInt(txt_mota.getText().trim()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "SDT phải là số!");
            txt_mota.requestFocus();
            return null; 
        }
    }
    
    return new ModelListFilm(id, hoten, HEIGHT, hoten, moTa);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tbl_Students = new com.project.table.Table();
        btn_Them = new com.raven.swing.Button();
        btn_sua = new com.raven.swing.Button();
        btn_xoa = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_hoten = new javax.swing.JTextField();
        txt_daoDien = new javax.swing.JTextField();
        txt_theLoai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data Danh Sách Phim");

        tbl_Students.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Duration", "Director", "Description"
            }
        ));
        tbl_Students.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentsMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_Students);

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setText("ID :");

        jLabel3.setText("Tên :");

        jLabel4.setText("Đạo diễn :");

        jLabel6.setText("Mô Tả :");

        jLabel8.setText("Thời gian :");

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hotenActionPerformed(evt);
            }
        });

        txt_daoDien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_daoDienActionPerformed(evt);
            }
        });

        txt_theLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_theLoaiActionPerformed(evt);
            }
        });

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane2.setViewportView(txt_mota);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_theLoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txt_daoDien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(243, 243, 243))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_hoten)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_daoDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_theLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void tbl_StudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentsMouseClicked
        index = tbl_Students.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_StudentsMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String id = txt_search.getText().toLowerCase();
        if (rp.timMa(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên phim để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim Thay Phim");
            fillTable(rp.timMa(id));
        }


    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        index = tbl_Students.getSelectedRow();
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

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
       index = tbl_Students.getSelectedRow();
       if(index<0){
           JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
       }else{
           String id = rp.getAll().get(index).getHoten();
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

    private void txt_theLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_theLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_theLoaiActionPerformed

    private void txt_hotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hotenActionPerformed

    private void txt_daoDienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_daoDienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_daoDienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Them;
    private com.raven.swing.Button btn_search;
    private com.raven.swing.Button btn_sua;
    private com.raven.swing.Button btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_Students;
    private javax.swing.JTextField txt_daoDien;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_theLoai;
    // End of variables declaration//GEN-END:variables

    private void isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
