package com.project.swing;

import com.project.event.EventMenu;
import com.project.event.EventMenuSelected;
import com.project.model.ModelMenu;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class MenuIteam extends javax.swing.JPanel {

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    public ModelMenu getMenu() {
        return menu;
    }

    private float alpha;
    private ModelMenu menu;
    private boolean open;
    private EventMenuSelected eventSelected;
    private int index;
    public MenuIteam( ModelMenu menu,EventMenu event, EventMenuSelected eventSelected, int index) {
        initComponents();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;
        setOpaque(false);
//        setForeground(new Color(255,255,255));
        setLayout(new MigLayout("wrap,fillx,insets 0", "[fill]","[fill,40!]"));
        MenuButton fisrtItem = new MenuButton(menu.getIcon(),"      "+menu.getMenuName());
       
               
        fisrtItem.setForeground(new Color(240,240,240));
        fisrtItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menu.getSubMenu().length>0){
                   if(event.menuPressed(MenuIteam.this, !open)){
                       open = !open;
                      
                   }
                }
                eventSelected.menuSelected(index, -1);
            }
        });
        add(fisrtItem);
        int subMenuIndex = -1;
        for(String st :menu.getSubMenu()){
            MenuButton item = new MenuButton(st);
            item.setIndex(++subMenuIndex);
            item.setForeground(new Color(240,240,240));
            item.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eventSelected.menuSelected(index, item.getIndex());
                    
                }
            });
            add(item);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getPreferredSize().height;
       Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         GradientPaint gra = new GradientPaint(0, 0, new Color(229,9,20), getWidth(), 0, new Color(0,0,0));
         g2.setPaint(gra);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.fillRect(0, 2, width, 38);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillRect(0, 40, width, height -40);
        g2.setColor(new Color(100,100,100));
//        g2.drawLine(30, 40, 30, height - 17);
//        for (int i = 0; i < menu.getSubMenu().length; i++) {
//            int y = ((i + 1)*35 + 40) -17;
//            g2.drawLine(30, y, 38, y);
//        }
        if(menu.getSubMenu().length>0){
            createArrowButton(g2);        }
        super.paintComponent(g); 
        
    }
    private void createArrowButton(Graphics2D g2){
            int size = 4;
            int y = 19;
            int x = 205;
            int height = getPreferredSize().height;
            
            g2.setColor( new Color(230,230,230));
            float ay = alpha * size;
            float ay1 = (1f - alpha) * size;
            g2.drawLine(x,(int)(y + ay), x + 4, (int) (y + ay1));
            g2.drawLine(x + 4, (int)(y + ay1), x + 8, (int)(y + ay));
            g2.drawLine(30, 40, 30, height - 17);
                    for (int i = 0; i < menu.getSubMenu().length; i++) {
             y = ((i + 1)*35 + 40) -17;
            g2.drawLine(30, y, 38, y);
        }
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
