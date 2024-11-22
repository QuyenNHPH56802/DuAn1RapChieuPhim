/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import Trailer_Video.Practice;
import com.project.model.Movie;
import com.project.swing.scrollbar.ModernScrollBarUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

/**
 *
 * @author admin
 */


public class Form_Main extends javax.swing.JPanel {

    private StringBuilder trailerPathBuilder;
    private int currentIndex = 0; // Biến để theo dõi vị trí phim hiện tại
    private MainForm main;
    
    

    /**
     * Creates new form Form_Main
     */
    public Form_Main() throws IOException {
        initComponents();
        setOpaque(false);
        
        

        ArrayList<Movie> movies = new ArrayList<>();
        read(movies);  // Phương thức read để thêm phim vào danh sách
        trailerPathBuilder = new StringBuilder();
        // Gọi phương thức innit() để hiển thị các phim
        innit(movies);
        

    }
    
    

    void read(ArrayList<Movie> movies) {

        movies.add(new Movie("Phim 1", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster9.1.png", "C:\\Users\\Admin\\Videos\\trailer1.mp4"));
        movies.add(new Movie("Phim 2", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster1.1.png", "C:\\Users\\Admin\\Videos\\trailer2.mp4"));
        movies.add(new Movie("Phim 3", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster2.1.png", "C:\\Users\\Admin\\Videos\\trailer3.mp4"));
        movies.add(new Movie("Phim 4", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster3.1.png", "C:\\Users\\Admin\\Videos\\trailer4.mp4"));
        movies.add(new Movie("Phim 5", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster4.1.png", "C:\\Users\\Admin\\Videos\\trailer5.mp4"));
        movies.add(new Movie("Phim 6", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster15.1.png","C:\\Users\\Admin\\Videos\\trailer6.mp4"));
        movies.add(new Movie("Phim 7", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster6.1.png", "C:\\Users\\Admin\\Videos\\trailer7.mp4"));
        movies.add(new Movie("Phim 8", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster7.1.png", "C:\\Users\\Admin\\Videos\\trailer8.mp4"));
        movies.add(new Movie("Phim 9", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster8.1.png", "C:\\Users\\Admin\\Videos\\trailer9.mp4"));

        movies.add(new Movie("Phim 10", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster16.1.png", "C:\\Users\\Admin\\Videos\\trailer11.mp4"));
        movies.add(new Movie("Phim 11", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster9.1.png", "C:\\Users\\Admin\\Videos\\trailer11.mp4"));
        movies.add(new Movie("Phim 12", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster11.1.png", "C:\\Users\\Admin\\Videos\\trailer12.mp4"));
        movies.add(new Movie("Phim 6", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster13.1.png", "C:\\Users\\Admin\\Videos\\trailer14.mp4"));
        movies.add(new Movie("Phim 7", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster5.1.png", "C:\\Users\\Admin\\Videos\\trailer15.mp4"));
        movies.add(new Movie("Phim 8", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster17.1.png", "C:\\Users\\Admin\\Videos\\trailer16.mp4"));
        movies.add(new Movie("Phim 9", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster18.2.png", "C:\\Users\\Admin\\Videos\\trailer17.mp4"));

        movies.add(new Movie("Phim 10", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster10.1.png", "C:\\Users\\Admin\\Videos\\trailer1.mp4"));
        movies.add(new Movie("Phim 11", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster14.1.jpg", "C:\\Users\\Admin\\Videos\\trailer1.mp4"));
        movies.add(new Movie("Phim 12", "C:\\Users\\ADMIN\\Pictures\\Picture\\poster11.png", "C:\\Users\\Admin\\Videos\\trailer1.mp4"));
    }

    public void show() {
        Ok main = new Ok();
    }

//    private void leftButton(ArrayList<Movie> movies) {
//        if (currentIndex > 0) {
//            currentIndex--;
//            updateGridPanel(panel1, movies);
//        }
//    }
//
//    private void rightButton(ArrayList<Movie> movies) {
//        if (currentIndex < movies.size() - 5) { // Còn poster để cuộn
//            currentIndex++;
//            updateGridPanel(panel1, movies);
//        }
//    }
    public void innit(ArrayList<Movie> movies) throws IOException {
        panel.setLayout(new BorderLayout()); // Panel chính

        // *** Hàng đầu tiên: Poster có chức năng cuộn ***
        JPanel firstRowPanel = new JPanel(new BorderLayout());
        JPanel firstRowGrid = new JPanel(new GridLayout(1, 5, 20, 20)); // Lưới 1 hàng, 5 cột
        firstRowGrid.setOpaque(false);
        firstRowPanel.setBackground(new Color(38, 38, 38, 85));

        // Cập nhật hàng đầu tiên
        updateFirstRow(firstRowGrid, movies);
        firstRowPanel.add(firstRowGrid, BorderLayout.CENTER);

        // Nút trái
        JButton leftButton = new JButton("<");
        leftButton.setPreferredSize(new Dimension(50, 50));
        leftButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                currentIndex = movies.size() - 5; // Quay về cuối
            }
            try {
                updateFirstRow(firstRowGrid, movies);
            } catch (IOException ex) {
                Logger.getLogger(Form_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            firstRowGrid.revalidate();
            firstRowGrid.repaint();
        });

        firstRowPanel.add(leftButton, BorderLayout.WEST);

        // Nút phải
        JButton rightButton = new JButton(">");
        rightButton.setPreferredSize(new Dimension(50, 50));
        rightButton.addActionListener(e -> {
            if (currentIndex < movies.size() - 5) {
                currentIndex++;
            } else {
                currentIndex = 0; // Quay về đầu
            }
            try {
                updateFirstRow(firstRowGrid, movies);
            } catch (IOException ex) {
                Logger.getLogger(Form_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            firstRowGrid.revalidate();
            firstRowGrid.repaint();

        });

        firstRowPanel.add(rightButton, BorderLayout.EAST);

        panel.add(firstRowPanel, BorderLayout.NORTH); // Thêm hàng đầu tiên vào trên cùng
        panel.setOpaque(false);

// Đảm bảo các thành phần con của panel không làm mất độ trong suốt
        leftButton.setOpaque(false);
        rightButton.setOpaque(false);
        firstRowGrid.setOpaque(false);

        // Các hàng tiếp theo: Poster cố định không có chức năng cuộn
        JPanel otherRowsPanel = new JPanel();
        otherRowsPanel.setLayout(new BoxLayout(otherRowsPanel, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout để tạo các hàng dọc
        int panelWidth = panel.getWidth(); // Lấy chiều rộng của panel
        int posterWidth = panelWidth / 4 - 20; // Chiều rộng mỗi poster (giảm bớt khoảng cách giữa các poster)
        int posterHeight = 180; // Chiều cao cố định cho poster

        otherRowsPanel.setOpaque(false); // Làm panel trong suốt
        // Duyệt qua tất cả các phim từ vị trí thứ 5 trở đi
        int postersPerRow = 4; // Số lượng poster trong mỗi hàng
        JPanel currentRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Một hàng poster mới

        for (int i = 5; i < movies.size(); i++) {
            Movie movie = movies.get(i);

            // Tạo panel cho mỗi phim
            JPanel moviePanel = createMoviePanel(movie, 300, 400); // Kích thước nhỏ hơn cho các hàng tiếp theo

            // Thêm poster vào hàng hiện tại
            currentRowPanel.add(moviePanel);

            // Nếu số lượng poster đã đủ 4, thì thêm hàng mới
            if (currentRowPanel.getComponentCount() == postersPerRow) {

                otherRowsPanel.add(currentRowPanel); // Thêm hàng vào panel chính
                currentRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Tạo hàng mới
            }
            currentRowPanel.setOpaque(false);
        }

        JScrollPane scrollPane = new JScrollPane(otherRowsPanel);

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

        panel.add(scrollPane, BorderLayout.CENTER); // Thêm các hàng còn lại vào trung tâm

        scrollPane.setOpaque(false); // Để ẩn nền của JScrollPane
        scrollPane.getViewport().setOpaque(false); // Để làm trong suốt Viewport

        // Làm thanh cuộn trong suốt (nếu muốn)
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        otherRowsPanel.revalidate();
        panel.revalidate();
        panel.repaint();
    }
//    public void innit(ArrayList<Movie> movies) {
////        panel.setLayout(new GridLayout(1, 5, 0, 0));
//            
//        panel1.setLayout(new BorderLayout());
//
//        JPanel gridPanel = new JPanel(new GridLayout(1, 5, 0, 0)); // Lưới 1 hàng, 5 cột
//        updateGridPanel(gridPanel, movies); // Hiển thị 5 poster đầu tiên
//        panel1.add(gridPanel, BorderLayout.CENTER);
//
//        gridPanel.setOpaque(false);
//
//        JPanel leftPanel = new JPanel();
//        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Giữ nút ở giữa
//        // Nút điều khiển phải
//        JButton leftButton = new JButton("<");
//        leftButton.setPreferredSize(new Dimension(50, 50));
//        leftButton.addActionListener(e -> {
//            if (currentIndex < movies.size() - 5) { // Còn poster để cuộn
//                currentIndex++;
//                updateGridPanel(gridPanel, movies);
//                panel1.revalidate();
//                panel1.repaint();
//            }else{
//                currentIndex = 0;
//            }
//        });
//        leftPanel.add(leftButton);
//        panel1.add(leftPanel, BorderLayout.WEST);
//        // Hiển thị 5 phim đầu tiên hoặc ít hơn nếu không đủ
//        for (int i = 0; i < Math.min(5, movies.size()); i++) {
//            Movie movie = movies.get(i);
//            JPanel singleMoviePanel = createMoviePanel(movie);
//            panel1.add(singleMoviePanel);
//        }
//
//        JPanel rightPanel = new JPanel();
//        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Giữ nút ở giữa
//        // Nút điều khiển phải
//        JButton rightButton = new JButton(">");
//        rightButton.setPreferredSize(new Dimension(50, 50));
//        rightButton.addActionListener(e -> {
//            if (currentIndex < movies.size() - 5) { // Còn poster để cuộn
//                currentIndex++;
//                updateGridPanel(gridPanel, movies);
//                panel1.revalidate();
//                panel1.repaint();
//            }else{
//                currentIndex = 0;
//            }
//        });
//        rightPanel.add(rightButton);
//        leftPanel.setOpaque(false);
//        rightPanel.setOpaque(false);
//        panel1.add(rightPanel, BorderLayout.EAST);
//        panel1.add(gridPanel, BorderLayout.CENTER); // Lưới poster ở giữa
//        panel1.add(leftPanel, BorderLayout.WEST);   // Nút trái ở bên trái
//        panel1.add(rightPanel, BorderLayout.EAST);
//        panel.setLayout(new GridLayout(0, 4, 20, 35));
//
//        for (Movie x : movies) {
//            JPanel singleMoviePanel = new JPanel();
//            singleMoviePanel.setLayout(new BorderLayout());
//            sp.setBorder(null);
//            panel.setBorder(null);
//            // Hiển thị poster phim với kích thước cố định
//            ImageIcon posterIcon = new ImageIcon(x.getPosterPath());
//            Image scaledImage = posterIcon.getImage().getScaledInstance(304, 170, Image.SCALE_SMOOTH);
//            JLabel posterLabel = new JLabel(new ImageIcon(scaledImage));
//            singleMoviePanel.add(posterLabel, BorderLayout.CENTER);
//            posterLabel.setOpaque(false);
//
//            // Thêm nút chức năng dưới mỗi poster
//            JButton actionButton = new JButton("Xem Chi Tiết");
//            actionButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    trailerPathBuilder.setLength(0); // xoa noidung truoc do
//                    trailerPathBuilder.append(x.getTrailerPath()); // luu duong dan trailer
//                    JOptionPane.showMessageDialog(null, "Xem chi tiết của phim: " + x.getTitle());
//                    showTrailer();
//                }
//            });
//            sp.addMouseWheelListener(new MouseWheelListener() {
//                @Override
//                public void mouseWheelMoved(MouseWheelEvent e) {
//                  int notches = e.getWheelRotation();
//                  int scrollAmount = 500;
//                  if(notches < 0){
//                      panel.scrollRectToVisible(new Rectangle(0,panel.getY()- scrollAmount,panel.getWidth(),panel.getHeight()));
//                  }else{
//                      panel.scrollRectToVisible(new Rectangle(0,panel.getY() + scrollAmount,panel.getWidth(),panel.getHeight()));
//                  }
//                }
//            });
//            posterLabel.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn đặt lịch không ?");
//                    if (chon == 0) {
//                        show();
//                    }
//                }
//            });
//              
//             singleMoviePanel.add(actionButton, BorderLayout.SOUTH);
////            singleMoviePanel.add(posterLabel, BorderLayout.SOUTH);
//
//            // Thêm từng panel phim vào panel chính
//            panel.add(singleMoviePanel);
//
//        }
//    }

    class PosterPanel extends JPanel {

        private Image poster;
        private int width;
        private int height;

        public PosterPanel(String posterPath, int width, int height) {
            this.width = width;
            this.height = height;

            // Tải ảnh từ đường dẫn
            this.poster = new ImageIcon(posterPath).getImage();
            setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Cải thiện chất lượng vẽ
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            // Vẽ ảnh đã được scale
            g2d.drawImage(poster, 0, 0, width, height, null);
        }
    }

    public JComponent createPosterComponent(String posterPath, int posterWidth, int posterHeight, boolean usePosterPanel) {
        if (usePosterPanel) {
            // Sử dụng PosterPanel để vẽ ảnh
            return new PosterPanel(posterPath, posterWidth, posterHeight);
        } else {
            // Sử dụng JLabel để hiển thị ảnh
            ImageIcon posterIcon = new ImageIcon(posterPath);
            Image scaledImage = posterIcon.getImage().getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        }
    }

    public JPanel createMoviePanel(Movie x, int posterWidth, int posterHeight) throws IOException {
        JPanel singleMoviePanel = new JPanel(new BorderLayout());
        main = new MainForm();
//        // Hiển thị poster phim với kích thước cố định
//        ImageIcon posterIcon = new ImageIcon(x.getPosterPath());
//        Image scaledImage = posterIcon.getImage().getScaledInstance(posterWidth, posterHeight, Image.SCALE_SMOOTH);
//        JLabel posterLabel = new JLabel(new ImageIcon(scaledImage));
        // Sử dụng PosterPanel để vẽ ảnh
        PosterPanel posterPanel = new PosterPanel(x.getPosterPath(), posterWidth, posterHeight);
        // Sử dụng thành phần poster từ createPosterComponent
//    JComponent posterComponent = createPosterComponent(x.getPosterPath(), posterWidth, posterHeight, true); // Chọn true hoặc false
        singleMoviePanel.add(posterPanel, BorderLayout.CENTER);

        posterPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                trailerPathBuilder.setLength(0); // Xóa nội dung trước đó
                trailerPathBuilder.append(x.getTrailerPath()); // Lưu đường dẫn trailer
               
                       try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Hỏi xem trailer
         JLabel trailerLabel = new JLabel("Bạn có muốn xem trailer không?");
        trailerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        trailerLabel.setForeground(new Color(0, 102, 204)); // Màu xanh đậm vừa

        JPanel trailerPanel = new JPanel(new BorderLayout());
        trailerPanel.add(trailerLabel, BorderLayout.CENTER);

        // Hiển thị JOptionPane với JLabel
        int chon = JOptionPane.showConfirmDialog(null, 
                trailerPanel, 
                "Xem Trailer", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE);
        if (chon == JOptionPane.YES_OPTION) {
            showTrailer();
        }

        // Tạo JLabel tùy chỉnh cho đặt lịch
        JLabel bookingLabel = new JLabel("Bạn có muốn đặt lịch cho " + x.getTitle() + " không?");
        bookingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bookingLabel.setForeground(new Color(0, 102, 204)); // Màu xanh đậm vừa

        JPanel bookingPanel = new JPanel(new BorderLayout());
        bookingPanel.add(bookingLabel, BorderLayout.CENTER);

        // Hiển thị JOptionPane với JLabel
        int bookingConfirmed = JOptionPane.showConfirmDialog(null, 
                bookingPanel, 
                "Xác nhận đặt lịch", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE);
        if (bookingConfirmed == JOptionPane.YES_OPTION) {
            main.showForm(new Ok());
        }
    }
});
//        // Nút xem chi tiết
//        JButton actionButton = new JButton("Xem Chi Tiết");
//        actionButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                trailerPathBuilder.setLength(0); // Xóa nội dung trước đó
//                trailerPathBuilder.append(x.getTrailerPath()); // Lưu đường dẫn trailer
//                JOptionPane.showMessageDialog(null, "Xem chi tiết của phim: " + x.getTitle());
//                showTrailer();
//            }
//        });
//
//        // Set chiều rộng của button bằng chiều rộng của ảnh
//        actionButton.setPreferredSize(new Dimension(scaledImage.getWidth(null), actionButton.getPreferredSize().height));
//
//        singleMoviePanel.add(actionButton, BorderLayout.SOUTH);

        // Căn giữa poster và button trong singleMoviePanel bằng FlowLayout
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Dùng FlowLayout.CENTER
        centerPanel.setOpaque(false);
        centerPanel.add(singleMoviePanel);// Căn giữa poster và button trong singleMoviePanel bằng FlowLayout

        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Khoảng cách từ trái (hgap = 20)
        return centerPanel;
    }

//    // Cập nhật panel để hiển thị phim tiếp theo/dưới
//    public void updateFirstRow(JPanel gridPanel, ArrayList<Movie> movies) {
//        gridPanel.removeAll();
//        for (int i = currentIndex; i < currentIndex + 5 && i < movies.size(); i++) {
////            gridPanel.add(createMoviePanel(movies.get(i)));
//            JPanel moviePanel = createMoviePanel(movies.get(i)); // Tạo panel cho từng phim
//            gridPanel.add(moviePanel); // Thêm vào gridPanel
//        }
//        gridPanel.revalidate();
//        gridPanel.repaint();
//    }
    // Cập nhật poster trong hàng đầu tiên
    public void updateFirstRow(JPanel gridPanel, ArrayList<Movie> movies) throws IOException {
        gridPanel.removeAll(); // Xóa các poster cũ
        for (int i = currentIndex; i < currentIndex + 5 && i < movies.size(); i++) {
            Movie movie = movies.get(i);
            JPanel moviePanel = createMoviePanel(movie, 220, 300);
            gridPanel.add(moviePanel);
        }
    }

    public void showTrailer() {
        String trailerPath = trailerPathBuilder.toString();
        // Đường dẫn đến VLC hoặc trình phát video khác
        String playerPath = "C:\\Program Files\\Windows Media Player\\wmplayer.exe"; // Thay đổi theo nhu cầu
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(playerPath, trailerPath);
            processBuilder.start(); // Khởi động video trailer
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
    }

    public void showTime() {
        Practice main = new Practice();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 83, 83));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/video-camera (2).png"))); // NOI18N
        jLabel1.setText("Danh Sách Phim Hot Nhất Tháng");

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(522, 522, 522)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
