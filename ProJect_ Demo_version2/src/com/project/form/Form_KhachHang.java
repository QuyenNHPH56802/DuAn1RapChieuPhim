/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.model.ExportExcel_KhachHang;
import com.project.model.ModelKhachHang;
import com.project.service.Responsitory_KhachHang;
import com.project.swing.scrollbar.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
public class Form_KhachHang extends javax.swing.JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private Responsitory_KhachHang rp = new Responsitory_KhachHang();
    private int index = -1;
    private ExportExcel_KhachHang main;
    /**
     * Creates new form Form_Teacher
     */
    public Form_KhachHang() {
        initComponents();
        setOpaque(false);
                GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa
        add(jLabel4, gbc);
Font arialFont = new Font("Arial", Font.PLAIN, 14);
        jLabel2.setFont(arialFont);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setFont(arialFont);
        jLabel3.setForeground(Color.WHITE);
        jLabel7.setFont(arialFont);
        jLabel7.setForeground(Color.WHITE);
        jLabel6.setFont(arialFont);
        jLabel6.setForeground(Color.WHITE);
        
        Font arialFont1 = new Font("Arial", Font.PLAIN, 20);
         jLabel4.setFont(arialFont1);
        jLabel4.setForeground(Color.WHITE);
        
              txt_id.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
              txt_id.setBackground(Color.white);
              txt_id.setFont(new Font("Arial", Font.PLAIN, 14));
              txt_id.setPreferredSize(new Dimension(250, 30));
              
                txt_hoten.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
              txt_hoten.setBackground(Color.white);
              txt_hoten.setFont(new Font("Arial", Font.PLAIN, 14));
              txt_hoten.setPreferredSize(new Dimension(250, 30));
              
                txt_gmail.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
              txt_gmail.setBackground(Color.white);
              txt_gmail.setFont(new Font("Arial", Font.PLAIN, 14));
              txt_gmail.setPreferredSize(new Dimension(250, 30));
              
                txt_sdt.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
              txt_sdt.setBackground(Color.white);
              txt_sdt.setFont(new Font("Arial", Font.PLAIN, 14));
              txt_sdt.setPreferredSize(new Dimension(250, 30));

        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        if(rp.getAll().isEmpty()){
            JOptionPane.showMessageDialog(null, "No data");
        }else{
            this.showData(index -1);
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER); 
        
        
        for (int i = 0; i < tbl_Teacher.getColumnCount(); i++) {
           tbl_Teacher.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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

    public void fillTable(ArrayList<ModelKhachHang> lists){
        model = (DefaultTableModel)tbl_Teacher.getModel();
        model.setRowCount(0);
        for (ModelKhachHang x : lists) {
            model.addRow(x.toDataRow());
        }
 
    }
    public void showData(int index){
         String id = tbl_Teacher.getValueAt(index,0).toString();
       String hoTen = tbl_Teacher.getValueAt(index,1).toString();
       String gmail = tbl_Teacher.getValueAt(index,2).toString();
       String sdt = tbl_Teacher.getValueAt(index,3).toString();
      
       txt_id.setText(id);
       txt_hoten.setText(hoTen);
       txt_gmail.setText(gmail);
       txt_sdt.setText(sdt); 
    }
    ModelKhachHang readForm(){
       int id = Integer.parseInt(txt_id.getText().trim());
        String hoten = txt_hoten.getText().trim();
        String namSinh = txt_sdt.getText().trim();
        String gmail = txt_gmail.getText().trim();
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
        txt_sdt.requestFocus();
        return null; 
    } else {
        try {
           int sdt = Integer.parseInt(txt_sdt.getText().trim()); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "SDT phải là số!");
            txt_sdt.requestFocus();
            return null; 
        }
         if (gmail.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Gmail không có dữ liệu");
        txt_gmail.requestFocus();
        return null; 
    }
       
        
    
    }
    
    return new ModelKhachHang(id, hoten, gmail, hoten);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tbl_Teacher = new com.project.table.Table();
        btn_Them3 = new com.raven.swing.Button();
        btn_sua3 = new com.raven.swing.Button();
        btn_xoa3 = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_hoten = new javax.swing.JTextField();
        txt_gmail = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setText("Họ và tên :");

        jLabel6.setText("Gmail :");

        jLabel7.setText("SĐT :");

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data Khách Hàng");

        tbl_Teacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Họ và tên", "SĐT", "Gmail"
            }
        ));
        tbl_Teacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TeacherMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_Teacher);

        btn_Them3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/plus (2).png"))); // NOI18N
        btn_Them3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them3ActionPerformed(evt);
            }
        });

        btn_sua3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btn_sua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua3ActionPerformed(evt);
            }
        });

        btn_xoa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/delete.png"))); // NOI18N
        btn_xoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa3ActionPerformed(evt);
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

        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(">");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btn_Them3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(23, 23, 23)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_sua3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoa3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Them3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
        );

        txt_hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hotenActionPerformed(evt);
            }
        });

        txt_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gmailActionPerformed(evt);
            }
        });

        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });

        jLabel2.setText("ID :");

        jButton1.setText("Danh Sách Khách Hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Quản Lý Danh Sách Khách Hàng");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(564, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_hoten)
                            .addComponent(txt_gmail)
                            .addComponent(txt_sdt))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(69, 69, 69))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void tbl_TeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TeacherMouseClicked
        index = tbl_Teacher.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_TeacherMouseClicked

    private void txt_gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gmailActionPerformed

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdtActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
           String id = txt_search.getText();
        if (rp.timTen(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SĐT khách hàng để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim Thay Khách Hàng");
            fillTable(rp.timTen(id));
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_Them3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them3ActionPerformed
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
    }//GEN-LAST:event_btn_Them3ActionPerformed

    private void btn_sua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua3ActionPerformed
        index = tbl_Teacher.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            String id = rp.getAll().get(index).getHoTen();
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
    }//GEN-LAST:event_btn_sua3ActionPerformed

    private void btn_xoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa3ActionPerformed
        index = tbl_Teacher.getSelectedRow();
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
    }//GEN-LAST:event_btn_xoa3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        main.exportCustomerData(rp.getAll());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_hotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hotenActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Them3;
    private com.raven.swing.Button btn_search;
    private com.raven.swing.Button btn_sua3;
    private com.raven.swing.Button btn_xoa3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_Teacher;
    private javax.swing.JTextField txt_gmail;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
