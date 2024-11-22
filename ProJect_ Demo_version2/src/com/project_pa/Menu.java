package com.project_pa;

import com.project.event.EventMenu;
import com.project.event.EventMenuSelected;
import com.project.model.ModelMenu;
import com.project.swing.MenuAnimation;
import com.project.swing.MenuIteam;
import com.project.swing.scrollbar.ModernScrollBarUI;
import com.project.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Menu extends javax.swing.JPanel {

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public boolean isShowMenu() {
        return showMenu;
    }
    private final MigLayout layout ;

    private EventMenuSelected event;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    

    public Menu() {
        initComponents();
        setOpaque(false);
        scrollPane3.getViewport().setOpaque(false);
        scrollPane3.setVerticalScrollBar(new ScrollBarCustom());
       panel3.setOpaque(false);
       scrollPane3.setViewportView(panel3);
        layout = new MigLayout("wrap, fillx, inset 0","[fill]","[]0[]");
        panel3.setLayout(layout);
       panel3.setBackground(new Color(0,0,0,0));
        
    }
    public  void initMenuItem(){
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/20.png")), "Phim Hot Tháng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/22.png")), "Danh Sách Film"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/23.png")), "Khách Hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/24.png")), "Quản Lý Tài Khoản"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/25.png")), "Quản Lý Lịch Chiếu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/26.png")), "Đặt Lịch"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/27.png")), "Quản Lý Ghế"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/28.png")), "Quản Lý Hóa Đơn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/29.png")),"Thống Kê","Top Film Booking","Top Khách Hàng Booking" ));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/30.png")), "Đăng Xuất"));
        scrollPane3.setOpaque(false);
    }
    private void addMenu(ModelMenu menu ){
        panel3.add(new MenuIteam(menu, getEvenMenu(), event, panel3.getComponentCount()),"h 40!");
    }
    
    
    private EventMenu getEvenMenu(){
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
               if(enableMenu){
                   if(showMenu){
                       if(open){
                           new MenuAnimation(layout,com).openMenu();
                       }else{
                           new MenuAnimation(layout,com).closeMenu();
                       }
                       return true;
                   }else{
                       System.out.println("Show pop up menu(Menu is close)");
                   }
               }
               return false;
            }
        };
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPane3 = new javax.swing.JScrollPane();
        panel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(18, 13, 13));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Netfix (3) (2).png"))); // NOI18N
        jLabel1.setText("NETFLIX");
        jLabel1.setOpaque(true);

        scrollPane3.setBorder(null);
        scrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3.setOpaque(false);

        panel3.setOpaque(false);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        scrollPane3.setViewportView(panel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane3)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(229,9,20), getWidth(), 0, new Color(0,0,0));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    // End of variables declaration//GEN-END:variables
}
