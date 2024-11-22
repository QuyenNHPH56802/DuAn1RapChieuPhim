
package com.project.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
 
public class MainForm extends javax.swing.JPanel {
private BufferedImage backgroundImage;
    private ImageIcon backgroundImageIcon;
        
    public MainForm() throws IOException {
        initComponents();
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,20,10,20));
      
    }
    public void setBackgroundDefault() throws IOException {
        backgroundImage = ImageIO.read(new File("C:\\Users\\ADMIN\\Pictures\\Picture\\1.jpg"));
        backgroundImageIcon = null;
        repaint(); // Vẽ lại panel với hình nền mặc định
    }
    public void showForm( Component form ){
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
    public void setBackgroundImage(String imagePath) throws IOException, IllegalAccessException {
    backgroundImage = ImageIO.read(new File(imagePath));
    File imageFile = new File(imagePath);
    if(!imageFile.exists()){
        throw new IllegalAccessException("Khong co anh dep " + imagePath);
    }
    if(imagePath.endsWith(".gif")){
         backgroundImageIcon = new ImageIcon(imagePath);
         backgroundImage = null;
    }else{
        backgroundImage = ImageIO.read(imageFile);
        backgroundImageIcon = null;
    }
   
    revalidate();
    repaint(); // Gọi repaint để vẽ lại panel với hình nền mới
}

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }else if(backgroundImageIcon!= null){
            backgroundImageIcon.paintIcon(this, g, 0, 0);
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
