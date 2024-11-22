package project_.demo;

import com.project.event.EventMenuSelected;
import com.project.form.BarChart;
import com.project.form.Form_DatVe;
import com.project.form.Form_NhanVien;
import com.project.form.Form_HoaDon;
import com.project.form.Form_Home;
import com.project.form.Form_QuanLyGhe;
import com.project.form.Form_LichChieu;
import com.project.form.Form_KhachHang;
import com.project.form.Form_Main;
import com.project.form.MainForm;
import com.project.form.Ok;
import com.project_pa.Header;
import com.project_pa.Menu;
import java.awt.MenuItem;
import com.project.swing.ButtonBadges;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author admin
 */
public class Main extends javax.swing.JFrame {
    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    
    public Main() throws IOException, IllegalAccessException {
        initComponents();
        init();
        this.setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void init() throws IOException, IllegalAccessException {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index :" + menuIndex + "SubMenu Index :" + subMenuIndex);
                if(menuIndex == 0){
                    
                    try {
                        main.showForm(new Form_Main());
                        main.setBackgroundImage("C:\\Users\\Admin\\Pictures\\My Wallpapers\\1.gif");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(menuIndex == 1){
                    main.showForm(new Form_Home());
                    try { 
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 2){
                    main.showForm(new Form_KhachHang());
                    try { 
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 3){
                    main.showForm(new Form_NhanVien());
                    try { 
                       main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 4){
                    main.showForm(new Form_LichChieu());
                    try { 
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 5){
                    main.showForm(new Ok());
                    try { 
                        
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\Picture\\1.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 6){
                    main.showForm(new Form_QuanLyGhe());                                
                    try { 
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 7){
                    main.showForm(new Form_HoaDon());
                    try { 
                        main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg");
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(menuIndex == 9){
                    System.exit(0);
                }else if(menuIndex == 8){
                      main.showForm(new BarChart());
                    if(menuIndex == 8 && subMenuIndex == 0){
                       
                    }else if(menuIndex == 8 && subMenuIndex == 1){
                        
                    }
                }
            }
        });
       
        menu.initMenuItem();
        bg.add(menu, "w 230!,spany 2");        
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double witdh;
                if (menu.isShowMenu()) {
                    witdh = 60 + (170 * (1f - fraction));
                } else {
                    witdh = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + witdh + "!, spany2");
                menu.revalidate();
            }
            
            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
            }
            
        };        
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();   
                    menu.setEnableMenu(true);
                }
                menu.setEnableMenu(false);
                if(menu.isShowMenu()){
                    // hide menu
                    
                }
            }
        });
        main.showForm(new Form_Main());
//        main.setBackgroundImage("C:\\Users\\Admin\\Pictures\\My Wallpapers\\wallpaper12.png");
            main.setBackgroundImage("C:\\Users\\ADMIN\\Pictures\\My Wrappers\\1.gif");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 997, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
