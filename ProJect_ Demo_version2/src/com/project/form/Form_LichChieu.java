/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.model.ModelLichChieu;
import com.project.service.Respository_LichChieu;
import com.project.swing.scrollbar.ModernScrollBarUI;
import com.project.table.ModelAction;
import java.awt.BorderLayout;
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
public class Form_LichChieu extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private Respository_LichChieu rp = new Respository_LichChieu();
    private int index = -1;

    public Form_LichChieu() {
        initComponents();
        setOpaque(false);
        
        Font arialFont = new Font("Arial", Font.PLAIN, 14);
        jLabel2.setFont(arialFont);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setFont(arialFont);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setFont(arialFont);
        jLabel4.setForeground(Color.WHITE);
        jLabel6.setFont(arialFont);
        jLabel6.setForeground(Color.WHITE);
        jLabel5.setFont(arialFont);
        jLabel5.setForeground(Color.WHITE);

        txt_ma.setBackground(Color.white);
txt_ma.setFont(new Font("Arial", Font.PLAIN, 14));
txt_ma.setPreferredSize(new Dimension(250, 30));


txt_ten.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_ten.setBackground(Color.white);
txt_ten.setFont(new Font("Arial", Font.PLAIN, 14));
txt_ten.setPreferredSize(new Dimension(250, 30));

txt_room.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_room.setBackground(Color.white);
txt_room.setFont(new Font("Arial", Font.PLAIN, 14));
txt_room.setPreferredSize(new Dimension(250, 30));

txt_tinChi.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_tinChi.setBackground(Color.white);
txt_tinChi.setFont(new Font("Arial", Font.PLAIN, 14));
txt_tinChi.setPreferredSize(new Dimension(250, 30));

txt_ngaychieu.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
txt_ngaychieu.setBackground(Color.white);
txt_ngaychieu.setFont(new Font("Arial", Font.PLAIN, 14));
txt_ngaychieu.setPreferredSize(new Dimension(250, 30));
        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        if (rp.getAll().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data");
        } else {
            this.showData(index - 1);
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER); 
        
       
        for (int i = 0; i < tbl_Subjects.getColumnCount(); i++) {
           tbl_Subjects.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
// Áp dụng giao diện ModernScrollBarUI cho thanh cuộn dọc và ngang
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

    public void fillTable(ArrayList<ModelLichChieu> lists) {
        model = (DefaultTableModel)tbl_Subjects.getModel();
        model.setRowCount(0);
        for (ModelLichChieu x : lists) {
            model.addRow(x.toDataRow());
        }
    }
    public void showData(int index){
       String id = tbl_Subjects.getValueAt(index,0).toString();
       String phimID = tbl_Subjects.getValueAt(index,1).toString();
       String room = tbl_Subjects.getValueAt(index,2).toString();
       String ngayChieu = tbl_Subjects.getValueAt(index, 3).toString();
       String thoiGian = tbl_Subjects.getValueAt(index, 4).toString();
       txt_ma.setText(id);
       txt_ten.setText(phimID);
       txt_tinChi.setText(thoiGian);
       txt_ngaychieu.setText(ngayChieu);
       txt_room.setText(room);
    
   }
        ModelLichChieu readForm(){
       int id = Integer.parseInt(txt_ma.getText().trim());
        String hoten = txt_ten.getText().trim();
        String room = txt_room.getText().trim();
        String ngayChieu = txt_ngaychieu.getText().trim();
        String thoiGian = txt_tinChi.getText().trim();
       
       if(txt_ma.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Khong co id");
           txt_ma.requestFocus();
           return null;
       }
       
        if(hoten.isEmpty()){
            JOptionPane.showMessageDialog(null, "Phim khong co du lieu");
            txt_ten.requestFocus();
            return null;
        }
        if (room.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Room không có dữ liệu");
        txt_room.requestFocus();
        return null; 
    }
         if (ngayChieu.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ngày Chiếu không có dữ liệu");
        txt_ngaychieu.requestFocus();
        return null; 
    }
       if (thoiGian.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Thời Gian không có dữ liệu");
        txt_tinChi.requestFocus();
        return null; 
    }  
        
    
    
    return new ModelLichChieu(id, id, id, ngayChieu, thoiGian);
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
        tbl_Subjects = new com.project.table.Table();
        btn_Them = new com.raven.swing.Button();
        btn_sua = new com.raven.swing.Button();
        btn_xoa = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ma = new javax.swing.JTextField();
        txt_ten = new javax.swing.JTextField();
        txt_tinChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_room = new javax.swing.JTextField();
        txt_ngaychieu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data Lịch Chiếu");

        tbl_Subjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Phim", "Room ", "Show Date", "Show Time"
            }
        ));
        tbl_Subjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SubjectsMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_Subjects);

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
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
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );

        jLabel2.setText("ID :");

        jLabel3.setText("Phim  :");

        jLabel4.setText("Thời Gian Bắt Đầu :");

        txt_ma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maActionPerformed(evt);
            }
        });

        txt_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenActionPerformed(evt);
            }
        });

        jLabel5.setText("Room :");

        jLabel6.setText("Ngày Chiếu :");

        jButton1.setText("Update All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 511, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_tinChi, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_ten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txt_ma, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_room, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_ngaychieu))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txt_room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txt_ngaychieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tinChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_SubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SubjectsMouseClicked
        index = tbl_Subjects.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_SubjectsMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
         String id = txt_search.getText().toLowerCase();
        if (rp.timMa(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim Thay Thời Gian Chiếu");
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
        index = tbl_Subjects.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            String id = rp.getAll().get(index).getThoiGianBatDau();
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
        index = tbl_Subjects.getSelectedRow();
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
           
                int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn sửa tất cả chứ ?");
                if(chon ==0){
                    JOptionPane.showMessageDialog(this, "Dữ liệu đã được sửa tất cả  !!!");
                    rp.updateAll();
                    fillTable(rp.getAll());
                }else{
                    JOptionPane.showMessageDialog(this, "Bạn không sửa tất cả dữ liệu !!!");
                    fillTable(rp.getAll());
                }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenActionPerformed

    private void txt_maActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Them;
    private com.raven.swing.Button btn_search;
    private com.raven.swing.Button btn_sua;
    private com.raven.swing.Button btn_xoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_Subjects;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JTextField txt_ngaychieu;
    private javax.swing.JTextField txt_room;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_tinChi;
    // End of variables declaration//GEN-END:variables
}
