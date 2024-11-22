/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.model.ModelMember;
import com.project.model.ModelLichChieu;

import com.project.service.Respository_Member;
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

/**
 *
 * @author admin
 */
public class Form_NhanVien extends javax.swing.JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private Respository_Member rp = new Respository_Member();
    private int index = -1;
    /**
     * Creates new form Form_Class
     */
    public Form_NhanVien() {
        initComponents();
        setOpaque(false);
        
        Font arialFont = new Font("Arial", Font.PLAIN, 14);
        jLabel2.setFont(arialFont);
        jLabel2.setForeground(Color.WHITE);
        jLabel3.setFont(arialFont);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setFont(arialFont);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setFont(arialFont);
        jLabel5.setForeground(Color.WHITE);
        
        txt_id.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_id.setBackground(Color.white);
        txt_id.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_id.setPreferredSize(new Dimension(250, 30));
        
        txt_taiKhoan.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_taiKhoan.setBackground(Color.white);
        txt_taiKhoan.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_taiKhoan.setPreferredSize(new Dimension(250, 30));
        
        txt_matKhau.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_matKhau.setBackground(Color.white);
        txt_matKhau.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_id.setPreferredSize(new Dimension(250, 30));
        
        txt_role.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        txt_role.setBackground(Color.white);
        txt_role.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_role.setPreferredSize(new Dimension(250, 30));
        this.fillTable(rp.getAll());
        index = rp.getAll().size();
        if(rp.getAll().isEmpty()){
            JOptionPane.showMessageDialog(null, "No data");
        }else{
            this.showData(index -1);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(jLabel1.CENTER);
        for (int i = 0; i < tbl_Class.getColumnCount(); i++) {
           tbl_Class.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //Áp dụng giao diện ModernScrollBarUI cho thanh cuộn dọc và ngang
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
public void fillTable(ArrayList<ModelMember> lists){
    model = (DefaultTableModel)tbl_Class.getModel();
        model.setRowCount(0);
        for (ModelMember x : lists) {
        model.addRow(x.toDataRow());
    }
}
public void showData(int index){
    String id = tbl_Class.getValueAt(index,0).toString();
       String tenLop = tbl_Class.getValueAt(index,1).toString();
       String sl = tbl_Class.getValueAt(index,2).toString();
       String trangThai = tbl_Class.getValueAt(index,3).toString();
      txt_id.setText(id);
      txt_taiKhoan.setText(tenLop);
      txt_matKhau.setText(sl);
      txt_role.setText(trangThai);
}
    ModelMember readForm(){
       int id = Integer.parseInt(txt_id.getText().trim());
        String taiKhoan = txt_taiKhoan.getText().trim();
        String matKhau = txt_matKhau.getText().trim();
        String role = txt_role.getText().trim();
        
       if(txt_id.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Khong co id");
           txt_id.requestFocus();
           return null;
       }
       
        if(taiKhoan.isEmpty()){
            JOptionPane.showMessageDialog(null, "Tài Khoản khong co du lieu");
            txt_taiKhoan.requestFocus();
            return null;
        }
        if (matKhau.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Mật Khẩu không có dữ liệu");
        txt_matKhau.requestFocus();
        return null; 
    }
         if (role.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Role không có dữ liệu");
        txt_role.requestFocus();
        return null; 
    }
      
    
    return new ModelMember(id, taiKhoan, matKhau, role);
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
        tbl_Class = new com.project.table.Table();
        btn_Them = new com.raven.swing.Button();
        btn_sua = new com.raven.swing.Button();
        btn_xoa = new com.raven.swing.Button();
        txt_search = new javax.swing.JTextField();
        btn_search = new com.raven.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_taiKhoan = new javax.swing.JTextField();
        txt_matKhau = new javax.swing.JTextField();
        txt_role = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Data User");

        tbl_Class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tài Khoản", "Mật Khẩu", "Role"
            }
        ));
        tbl_Class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClassMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tbl_Class);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
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
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        jLabel2.setText("ID :");

        jLabel3.setText("Tài Khoản  :");

        jLabel4.setText("Mật Khẩu :");

        jLabel5.setText("Role :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id)
                    .addComponent(txt_taiKhoan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_matKhau)
                    .addComponent(txt_role))
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)))))
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_role, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_taiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_ClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClassMouseClicked
        index = tbl_Class.getSelectedRow();
        showData(index);
    }//GEN-LAST:event_tbl_ClassMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
         String id = txt_search.getText().toLowerCase();
        if (rp.timTen(id).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản để tìm kiếm!");
            fillTable(rp.getAll());
        }else{
            JOptionPane.showMessageDialog(null, "Tim thấy tài khoản");
            fillTable(rp.timTen(id));
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
        index = tbl_Class.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào !!!");
        }else{
            String id = rp.getAll().get(index).getTaiKhoan();
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
        index = tbl_Class.getSelectedRow();
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private com.project.table.Table tbl_Class;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_matKhau;
    private javax.swing.JTextField txt_role;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_taiKhoan;
    // End of variables declaration//GEN-END:variables
}
