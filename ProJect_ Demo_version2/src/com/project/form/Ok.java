/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.project.model.Export_Bill;
import com.project.model.ModelKhachHang;
import com.project.model.ModelMember;
import com.project.model.SaveLocation;
import com.project.service.Responsitory_HoaDon;
import com.project.service.Responsitory_KhachHang;
import com.project.service.Respository_Member;
import com.project.service.Respository_Payment;
import com.project.utils.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Ok extends javax.swing.JPanel {

    private Responsitory_KhachHang rp1 = new Responsitory_KhachHang();

    public Ok() {
        initComponents();
        setOpaque(false);
        showCustomerInfoForm();

    }
    private ArrayList<Combo> selectedCombos = new ArrayList<>();
    private ArrayList<Integer> quantities = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<>();
    private Respository_Payment rp = new Respository_Payment();
    private Export_Bill bill = new Export_Bill();
    private static ArrayList<Seat> selectedSeats = new ArrayList<>();
    private static JPanel seatPanel;
    private static int currentShowCalendarId;
    private static int memberId; // ID của thành viên đã đặt ghế
    private static String movieName;
    private static String room;
    private static String date;
    private static String time;
    private static String currentMovieName;
    private static String currentRoomName;
    private static String currentBookingDate;
    private static String currentBookingTime;
    private MainForm main;
    private BufferedImage backgroundImage;
    private static double discount;

    public void showCustomerInfoForm() {
        JFrame frame = createFrame("Customer Information", 400, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Trường tìm kiếm
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Chiếm hai cột
        JLabel searchLabel = new JLabel("Search Customer:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(searchLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Chiếm một cột
        JTextField searchField = new JTextField(20); // Kích thước lớn hơn cho trường tìm kiếm
        frame.add(searchField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1; // Nút tìm kiếm bên cạnh trường tìm kiếm
        JButton searchButton = createButton("Search", e -> showCustomerList(searchField.getText()));
        searchButton.setPreferredSize(new Dimension(80, 25)); // Kích thước nhỏ hơn cho nút
        frame.add(searchButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Chiếm hai cột
        JButton newCustomerButton = createButton("New Customer", e -> {
            showNewCustomerForm(frame);
            frame.dispose();
        });
        frame.add(newCustomerButton, gbc);

        // Tùy chỉnh giao diện
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Nền màu xám nhạt
        frame.setVisible(true);

    }

    private void showNewCustomerForm(JFrame parentFrame) {
        JFrame frame = createFrame("New Customer Information", 400, 300);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Trường tên
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Name:"), gbc);
        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(nameField, gbc);

        // Trường điện thoại
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Phone:"), gbc);
        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(phoneField, gbc);

        // Trường email
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(new JLabel("Email:"), gbc);
        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(emailField, gbc);

        // Nút tiếp tục
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Chiếm hai cột
        JButton nextButton = createButton("Proceed to Booking", e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (validateCustomerInfo(name, phone, email)) {
                if (saveCustomerInfo(name, phone, email)) {
                    showBookingForm(name);
                    frame.dispose();
                    parentFrame.dispose();
                } else {
                    showError(frame, "Error saving customer information.");
                }
            }
        });
        frame.add(nextButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton nextButton1 = createButton("Back", e -> {
            showCustomerInfoForm();
            frame.dispose();
        });

        frame.add(nextButton1, gbc);

        // Tùy chỉnh giao diện
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Nền màu xám nhạt
        nextButton.setBackground(new Color(100, 150, 200)); // Màu nền nút
        nextButton.setForeground(Color.WHITE); // Màu chữ nút
        nextButton.setFocusPainted(false); // Không hiển thị viền khi nhấn nút

        frame.setVisible(true);
    }

    private void showCustomerList(String searchTerm) {
        JFrame frame = createFrame("Customer List", 400, 300);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> customerList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(customerList);

        String sql = "SELECT id, name FROM tbl_members WHERE name LIKE ?";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listModel.addElement(id + ": " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = customerList.getSelectedValue();
                if (selectedValue != null) {
                    memberId = Integer.parseInt(selectedValue.split(":")[0]);
                    showBookingForm(selectedValue.split(":")[1]);
                    frame.dispose();
                }
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private boolean saveCustomerInfo(String name, String phone, String email) {
        String sql = "INSERT INTO tbl_members (name, phone, email) VALUES (?, ?, ?)";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                memberId = generatedKeys.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở rộng toàn bộ màn hình
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private void showBookingForm(String customerName) {
        JFrame frame = createFrame("Book Movie Tickets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setUndecorated(true);
        // Load animated GIF as ImageIcon
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\ADMIN\\Pictures\\My Wrappers\\1.gif");

        // Use JLabel to hold the background
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout()); // Allows adding components over it

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Book Movie Tickets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundLabel.add(titleLabel, gbc);

        // Movie Selection
        JLabel movieLabel = new JLabel("Movie Title:");
        movieLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        backgroundLabel.add(movieLabel, gbc);

        JComboBox<String> cmbMovies = new JComboBox<>(getMovies());
        cmbMovies.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundLabel.add(cmbMovies, gbc);

        // Room Selection
        JLabel roomLabel = new JLabel("Room:");
        roomLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundLabel.add(roomLabel, gbc);

        JComboBox<String> cmbRooms = new JComboBox<>(getRooms());
        cmbRooms.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundLabel.add(cmbRooms, gbc);

        // Date Selection
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundLabel.add(dateLabel, gbc);

        JComboBox<String> cmbDates = new JComboBox<>(getShowDates());
        cmbDates.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundLabel.add(cmbDates, gbc);

        // Time Selection
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        backgroundLabel.add(timeLabel, gbc);

        JComboBox<String> cmbHours = new JComboBox<>();
        cmbHours.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 4;
        backgroundLabel.add(cmbHours, gbc);

        // Select Seats Button
        JButton btnSelectSeats = createStyledButton("Select Seats", e -> {
currentShowCalendarId = getShowCalendarId(
                    cmbMovies.getSelectedItem().toString(),
                    cmbRooms.getSelectedItem().toString(),
                    cmbDates.getSelectedItem().toString(),
                    cmbHours.getSelectedItem().toString()
            );
            currentMovieName = cmbMovies.getSelectedItem().toString();
            currentRoomName = cmbRooms.getSelectedItem().toString();
            currentBookingDate = cmbDates.getSelectedItem().toString();
            currentBookingTime = cmbHours.getSelectedItem().toString();

            if (currentShowCalendarId != -1) {
                showSeatSelectionForm(customerName, currentRoomName);
                frame.dispose();
            } else {
                showError(frame, "No schedule found for your selection.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        backgroundLabel.add(btnSelectSeats, gbc);

        // Cancel Button
        JButton btnCancel = createStyledButton("Cancel", e -> {
            // Go back to the customer info form
            showCustomerInfoForm();
            frame.dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 6; // Position it below the Select Seats button
        gbc.gridwidth = 2;
        backgroundLabel.add(btnCancel, gbc);

        // Room Selection Listener
        cmbRooms.addActionListener(e -> updateShowTimes((String) cmbRooms.getSelectedItem(), cmbHours));

        // Add background label to frame
        frame.add(backgroundLabel);
        frame.setSize(800, 600); // Set a preferred frame size
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void updateShowTimes(String roomName, JComboBox<String> cmbHours) {
        cmbHours.removeAllItems();
        String roomId = roomName.equals("Room A") ? "1" : "2"; // Example room IDs
        String[] availableTimes = getShowTimes(roomId);
        for (String time : availableTimes) {
            cmbHours.addItem(time);
        }
    }

    private String[] getShowTimes(String roomId) {
        ArrayList<String> times = new ArrayList<>();
        String sql = "SELECT DISTINCT show_time FROM tbl_showcalendar WHERE room_id = ?";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Time showTime = rs.getTime("show_time");
                if (showTime != null) {
                    String formattedTime = String.format("%tH:%tM:%tS", showTime, showTime, showTime);
                    times.add(formattedTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return times.toArray(new String[0]);
    }

    public int getShowCalendarId(String movieName, String room, String date, String time) {
        String sql = "SELECT id FROM tbl_showcalendar "
                + "WHERE movie_id = (SELECT id FROM tbl_movie WHERE name = ?) "
                + "AND room_id = (SELECT id FROM tbl_room WHERE name = ?) "
                + "AND show_date = ? AND show_time = ?";

        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, movieName); // Gán tên phim
            pstmt.setString(2, room);      // Gán tên phòng
            pstmt.setString(3, date);      // Gán ngày chiếu (định dạng YYYY-MM-DD)
            pstmt.setString(4, time);      // Gán giờ chiếu (định dạng HH:mm:ss)

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id"); // Trả về ID của lịch chiếu
            }
        } catch (SQLException e) {
            System.err.println("Error getting show calendar ID: " + e.getMessage());
        }

        return -1; // Trả về -1 nếu không tìm thấy hoặc xảy ra lỗi
    }

    public void loadCombos(JPanel comboPanel) throws IOException {
        String sql = "SELECT food_name, food_price, food_img FROM combo_food";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            comboPanel.setLayout(new GridLayout(0, 5, 30, 15)); // Layout setup

            int itemCount = 0; // Counter for the number of items processed

            while (rs.next()) {
                String foodName = rs.getString("food_name");
                int foodPrice = rs.getInt("food_price");
                String foodImgPath = rs.getString("food_img");

                prices.add(foodPrice); // Store price

                JPanel foodPanel = new JPanel();
                foodPanel.setLayout(new BorderLayout());
                foodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                ImageIcon foodImage = new ImageIcon(foodImgPath);
                Image img = foodImage.getImage().getScaledInstance(280, 150, Image.SCALE_SMOOTH);
                foodImage = new ImageIcon(img);
                JLabel imageLabel = new JLabel(foodImage);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                foodPanel.add(imageLabel, BorderLayout.NORTH);
                imageLabel.setOpaque(false);

                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new GridLayout(2, 1));
                JLabel infoLabel = new JLabel(foodName + " - " + foodPrice + " VND", SwingConstants.CENTER);
                infoPanel.add(infoLabel);
                foodPanel.add(infoPanel, BorderLayout.CENTER);
                infoPanel.setOpaque(false);

                // Only add quantity controls for the first five items
                if (itemCount < 5) {
                    quantities.add(0); // Initialize quantity

                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new GridLayout(3, 1));
                    JLabel quantityLabel = new JLabel("0", SwingConstants.CENTER);
                    quantityLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    buttonPanel.add(quantityLabel);

                    final int currentIndex = itemCount; // Capture current index
                    JButton addButton = new JButton("Add");
                    addButton.addActionListener(e -> {
                        int currentQuantity = Integer.parseInt(quantityLabel.getText());
                        currentQuantity++;
                        quantityLabel.setText(String.valueOf(currentQuantity));
                        quantities.set(currentIndex, currentQuantity); // Update quantity

                        // Add the combo to selectedCombos if quantity is increased
                        if (currentQuantity == 1) { // Only add if quantity was 0 before
                            selectedCombos.add(new Combo(foodName, foodPrice)); // Create and add the new Combo
                        }
                    });
                    buttonPanel.add(addButton);

                    JButton subtractButton = new JButton("Subtract");
                    subtractButton.addActionListener(e -> {
                        int currentQuantity = Integer.parseInt(quantityLabel.getText());
                        if (currentQuantity > 0) {
                            currentQuantity--;
                            quantityLabel.setText(String.valueOf(currentQuantity));
                            quantities.set(currentIndex, currentQuantity); // Update quantity

                            // Remove the combo from selectedCombos if quantity reaches 0
                            if (currentQuantity == 0) {
                                selectedCombos.removeIf(combo -> combo.getName().equals(foodName) && combo.getPrice() == foodPrice);
                            }
                        }
                    });
                    buttonPanel.add(subtractButton);

                    foodPanel.add(buttonPanel, BorderLayout.SOUTH);
                }

                comboPanel.add(foodPanel);
                itemCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboPanel.revalidate();
        comboPanel.repaint();
    }

    public void showComboSelectionForm(String customerName) throws IOException {
        JFrame comboFrame = createFrame("Select Combo");
        comboFrame.setSize(400, 300);
        comboFrame.setLayout(new BorderLayout());

        // Load the background image
        Image backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\Admin\\Pictures\\Picture\\quyen1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;  // Exit if the image cannot be loaded
        }

        // Create a panel with a custom paint method for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout()); // Use BorderLayout for the background panel

        // Load combos from the database and display them
        JPanel comboPanel = new JPanel(new GridLayout(0, 1));
        loadCombos(comboPanel); // Method to load combo options into the panel
        comboPanel.setOpaque(false);

        backgroundPanel.add(comboPanel, BorderLayout.CENTER);

        // Button to confirm selection
        JButton btnConfirmCombo = createButton("Confirm Combo", e -> {
            // Logic to handle selected combo
            // You might want to store the selected combo details
            showPaymentForm(customerName); // Proceed to payment
            comboFrame.dispose();
        });

        backgroundPanel.add(btnConfirmCombo, BorderLayout.SOUTH);
        comboFrame.setContentPane(backgroundPanel); // Add the background panel to the frame
        comboFrame.setVisible(true);
    }

    private void showSeatSelectionForm(String customerName, String roomName) {
        JFrame frame = createFrame("Select Seats"); // Không cần kích thước
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở rộng toàn bộ màn hình
        frame.setLayout(new BorderLayout());

        // Tạo label Màn hình
        JLabel screenLabel = new JLabel("Màn hình", SwingConstants.CENTER);
        screenLabel.setOpaque(true);
        screenLabel.setBackground(Color.GRAY); // Màu nền xám
        screenLabel.setForeground(Color.WHITE); // Màu chữ trắng
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt font chữ lớn

        // Thêm label Màn hình vào phần trên cùng của frame
        frame.add(screenLabel, BorderLayout.NORTH);

        // Panel chứa các nút ghế
        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(5, 10));
        loadSeatsFromDatabase(roomName); // Load seats from the database

        // Thêm panel ghế vào frame
        frame.add(seatPanel, BorderLayout.CENTER);

        // Tạo panel thông tin cho các loại ghế
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        infoPanel.add(createSeatInfoLabel("VIP Seats: Yellow", Color.YELLOW));
        infoPanel.add(createSeatInfoLabel("Regular Seats: Green", Color.GREEN));
        infoPanel.add(createSeatInfoLabel("Booked Seats: Red", Color.RED));

        // Nút chọn ghế
// Nút chọn ghế
        JButton btnSelectSeats = createButton("Choose Seats", e -> {
            if (selectedSeats.isEmpty()) {
                showError(frame, "Please choose seats before booking.");
            } else {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Do you want to buy a combo for popcorn and drinks?",
                        "Combo Selection",
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    try {
                        showComboSelectionForm(customerName);
                    } catch (IOException ex) {
                        Logger.getLogger(Ok.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame.dispose();
                } else {
                    showPaymentForm(customerName);
                    frame.dispose();
                }
            }
        });

        // Thêm panel thông tin ghế và nút vào phần dưới cùng của frame
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(infoPanel, BorderLayout.NORTH);
        bottomPanel.add(btnSelectSeats, BorderLayout.SOUTH);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Helper method to create a label for seat information
    private JLabel createSeatInfoLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private void loadSeatsFromDatabase(String roomName) {
        String sql = "SELECT seat_number, seat_type, member_id FROM tbl_seats WHERE id_showcalendar = ?";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, currentShowCalendarId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String seatNumber = rs.getString("seat_number");
                String seatType = rs.getString("seat_type");
                int bookedMemberId = rs.getInt("member_id"); // Retrieve the member ID directly

                createSeatButton(seatNumber, bookedMemberId, seatType); // Create button with the retrieved values
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSeatButton(String seatName, int bookedMemberId, String seatType) {
        JButton seatButton = new JButton(seatName);
        seatButton.setFont(new Font("Arial", Font.BOLD, 20)); // Chỉnh sửa kích thước font chữ ở đây

        // Check the member ID to determine the seat's booking status
        if (bookedMemberId > 0) {
            // Seat is booked
            seatButton.setBackground(Color.RED); // Fill booked seats with red
        } else {
            // Seat is available
            if ("VIP".equals(seatType)) {
                seatButton.setBackground(Color.YELLOW); // VIP seats
            } else {
                seatButton.setBackground(Color.GREEN); // Regular seats
            }
        }

        seatButton.addActionListener(new SeatButtonListener(seatButton, seatName, seatType, bookedMemberId));
        seatPanel.add(seatButton);
        seatPanel.revalidate();
        seatPanel.repaint();
    }

    private class SeatButtonListener implements ActionListener {

        private final JButton seatButton;
        private final String seatName;
        private final String seatType;
        private final int bookedMemberId;

        public SeatButtonListener(JButton seatButton, String seatName, String seatType, int bookedMemberId) {
            this.seatButton = seatButton;
            this.seatName = seatName;
            this.seatType = seatType;
            this.bookedMemberId = bookedMemberId;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bookedMemberId > 0) {
                showError(seatButton.getParent().getParent().getParent(), "This seat is already booked and cannot be canceled.");
                return; // Prevent any action on already booked seats
            }

            // Proceed with selecting or deselecting the seat
            if (seatButton.getBackground() == Color.GREEN || seatButton.getBackground() == Color.YELLOW) {
                selectedSeats.add(new Seat(seatName));
                seatButton.setBackground(Color.RED); // Mark as booked
                updateSeatInDatabase(seatName, memberId); // Save member ID for the seat
            } else if (seatButton.getBackground() == Color.RED) {
                selectedSeats.removeIf(seat -> seat.getName().equals(seatName)); // Remove seat from selection
                seatButton.setBackground(seatType.equals("VIP") ? Color.YELLOW : Color.GREEN); // Reset to available color
                updateSeatInDatabase(seatName, 0); // Set member ID to 0 for cancellation
            }
        }
    }

    private void updateSeatInDatabase(String seatName, int memberId) {
        String sql = "UPDATE tbl_seats SET member_id = ? WHERE seat_number = ? AND id_showcalendar = ?";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.setString(2, seatName);
            pstmt.setInt(3, currentShowCalendarId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double validateVoucher(String code) {
        double discount = 0.0;

        // SQL query to check the voucher
        String query = "SELECT discount FROM tbl_vouchers WHERE code = ? AND is_active = 1";

        // Use your JDBC connection class to get the connection
        try (Connection conn = DBConnect.getConnection(); // Replace with your connection method
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, code); // Set the voucher code parameter
            ResultSet rs = pstmt.executeQuery();

            // If a valid voucher is found, get the discount
            if (rs.next()) {
                discount = rs.getDouble("discount");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions (e.g., logging)
        }

        return discount; // Return the discount percentage or 0 if invalid
    }

    private void showPaymentForm(String customerName) {
        JFrame frame = createFrame("Payment", 400, 400);
        // Mở rộng toàn bộ màn hình
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Tải hình ảnh nền
        Image backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\ADMIN\\Pictures\\picture2\\5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return;  // Thoát nếu không tải được hình ảnh
        }

        // Tạo panel với hình nền
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Payment Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Đặt kích thước font lớn hơn
        titleLabel.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across
        backgroundPanel.add(titleLabel, gbc);

        // Selected Seats
        JLabel lblSelectedSeats = new JLabel("Selected Seats: " + getSelectedSeatsString());
        lblSelectedSeats.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSelectedSeats.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        backgroundPanel.add(lblSelectedSeats, gbc);

        // Selected Combos
        JLabel lblSelectedCombos = new JLabel("Selected Combos: " + getSelectedCombosString());
        lblSelectedCombos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSelectedCombos.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        backgroundPanel.add(lblSelectedCombos, gbc);

        // Total Price
        MutableDouble total = new MutableDouble(calculateTotalPrice());
        // Sử dụng DecimalFormat để định dạng giá trị tiền
        DecimalFormat df = new DecimalFormat("#,### ");

// Định dạng giá trị total.value
        String formattedTotal = df.format(total.value);
        JLabel lblTotal = new JLabel("Total Price: " + formattedTotal + " VND");
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTotal.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 3;
gbc.gridwidth = 2;
        backgroundPanel.add(lblTotal, gbc);

        // Voucher Input
        JLabel lblVoucher = new JLabel("Enter Voucher Code:");
        lblVoucher.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        backgroundPanel.add(lblVoucher, gbc);

        JTextField txtVoucher = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        backgroundPanel.add(txtVoucher, gbc);

        // Validate Voucher Button
        JButton btnValidateVoucher = new JButton("Nhập voucher");
        btnValidateVoucher.addActionListener(e -> {
            String voucherCode = txtVoucher.getText();
            double discount = validateVoucher(voucherCode);

            if (discount > 0) {
                total.value -= total.value * (discount / 100); // Apply discount
                String formattedTotal1 = df.format(total.value);
                lblTotal.setText("Total Price: " + formattedTotal1 + " VND (Discount applied: " + discount + "%)");
                txtVoucher.setEnabled(false);
                btnValidateVoucher.setEnabled(false);
            } else {
                showError(frame, "Voucher code is invalid.");
            }
        });

        // Add the button to the frame
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        backgroundPanel.add(btnValidateVoucher, gbc);

        // Payment Method
        JLabel lblPaymentMethod = new JLabel("Select Payment Method:");
        lblPaymentMethod.setForeground(Color.WHITE); // Đổi màu chữ cho dễ đọc
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        backgroundPanel.add(lblPaymentMethod, gbc);

        String[] paymentMethods = {"Credit Card", "Cash", "Debit Card", "PayPal"};
        JComboBox<String> cmbPaymentMethod = new JComboBox<>(paymentMethods);
        gbc.gridx = 1;
        gbc.gridy = 6;
        backgroundPanel.add(cmbPaymentMethod, gbc);

        // Change Seats Button
        JButton btnChangeSeats = createButton("Change Seats", e -> {
            for (Seat seat : selectedSeats) {
                updateSeatInDatabase(seat.name, 0);
            }
//            selectedSeats.clear();
            
            showSeatSelectionForm(customerName, currentRoomName);
            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        backgroundPanel.add(btnChangeSeats, gbc);

        // Change Combos Button
        JButton btnChangeCombos = createButton("Change Combos", e -> {
            selectedCombos.clear();
            quantities.clear();
            prices.clear();

            total.value = calculateTotalPrice();

            DecimalFormat df1 = new DecimalFormat("#,### ");
            String formattedTotal1 = df.format(total.value);
            lblTotal.setText("Total Price: " + formattedTotal + " VND");

            try {
showComboSelectionForm(customerName);
            } catch (IOException ex) {
                Logger.getLogger(Ok.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        backgroundPanel.add(btnChangeCombos, gbc);

        // Pay Button
        JButton btnPay = createButton("Pay", e -> {
            for (Seat seat : selectedSeats) {
                updateSeatInDatabase(seat.name, memberId);
            }
            // Lưu thông tin đặt chỗ

            String saveLocation = SaveLocation.chooseSave();
            if (saveLocation == null) {
                JOptionPane.showMessageDialog(null, "Đã hủy chọn thư mục");
                return;
            }

            ModelKhachHang model = rp1.getID(memberId);
            String tenKhachHang = model.getHoTen();
            String taiKhoanNganHang = "0366913731";
            Export_Bill bill = new Export_Bill();

            String selectedPaymentMethod1 = (String) cmbPaymentMethod.getSelectedItem();
            discount = validateVoucher(txtVoucher.getText());
            try {
                bill.createBill(saveLocation, tenKhachHang, rp.getID(memberId), taiKhoanNganHang, memberId, total.value, selectedPaymentMethod1);
            } catch (IOException ex) {
                Logger.getLogger(Ok.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(Ok.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(Ok.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Hoá đơn đã được tạo :" + saveLocation);

            String voucherCode = txtVoucher.getText();
            int bookingId = saveBookingInfo(total.value, selectedCombos, quantities, voucherCode);
            if (bookingId != -1) {
                String selectedPaymentMethod = (String) cmbPaymentMethod.getSelectedItem();
                insertPaymentRecord(bookingId, total.value, selectedPaymentMethod);
                JOptionPane.showMessageDialog(frame, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                showError(frame, "Error occurred while saving booking.");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        backgroundPanel.add(btnPay, gbc);

        // Cancel Booking Button
        JButton btnCancel = createButton("Cancel Booking", e -> {
            for (Seat seat : selectedSeats) {
                updateSeatInDatabase(seat.name, 0);
            }

            selectedCombos.clear();
            quantities.clear();
            prices.clear();

            selectedSeats.clear();

            total.value = 0;
DecimalFormat df2 = new DecimalFormat("#,### ");
            String formattedTotal2 = df.format(total.value);
            lblTotal.setText("Total Price: " + formattedTotal + " VND");
            JOptionPane.showMessageDialog(frame, "Booking canceled!", "Canceled", JOptionPane.WARNING_MESSAGE);
            frame.dispose();
        });
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        backgroundPanel.add(btnCancel, gbc);

        // Thêm panel nền vào frame
        frame.getContentPane().add(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public int saveBookingInfo(double total, ArrayList<Combo> selectedCombos, ArrayList<Integer> quantities, String voucherCode) {
        String sqlInsert = "INSERT INTO tbl_bookings (movie_name, room, booking_date, booking_time, seat_ids, member_id, quantity, total, status, combo_food, quality_food, voucher_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Paid', ?, ?, ?)";
        String sqlSelectQualityFood = "SELECT quality_food FROM tbl_bookings WHERE member_id = ? AND movie_name = ? AND booking_date = ? AND booking_time = ?";
        String sqlVoucherCheck = "SELECT id, discount FROM tbl_vouchers WHERE code = ? AND is_active = 1";
        DecimalFormat df = new DecimalFormat("#,###");
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmtSelectQualityFood = connection.prepareStatement(sqlSelectQualityFood); PreparedStatement pstmtInsert = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS); PreparedStatement pstmtVoucherCheck = connection.prepareStatement(sqlVoucherCheck)) {

            // Initialize voucherId
            String voucherId = null;

            // Check voucher validity and get its ID
            pstmtVoucherCheck.setString(1, voucherCode);
            ResultSet rsVoucher = pstmtVoucherCheck.executeQuery();

            if (rsVoucher.next()) {

                voucherId = rsVoucher.getString("id"); // Get voucher ID
                double discount = rsVoucher.getDouble("discount"); // Get discount if valid
                // Optionally apply discount to total if needed
                total -= total * (discount / 100);
            } else {
                System.out.println("Invalid voucher code.");
                return -1; // Return -1 or handle error appropriately
            }

            // Querying the current quality_food
            pstmtSelectQualityFood.setInt(1, memberId);
            pstmtSelectQualityFood.setString(2, currentMovieName);
            pstmtSelectQualityFood.setString(3, currentBookingDate);
            pstmtSelectQualityFood.setString(4, currentBookingTime);

            ResultSet resultSet = pstmtSelectQualityFood.executeQuery();
            int existingQualityFood = 0;
            if (resultSet.next()) {
                existingQualityFood = resultSet.getInt("quality_food");
            }

            // Prepare combo food names and their quantities
            StringBuilder comboNames = new StringBuilder();
            int totalQuantity = 0; // To accumulate total quantity

            for (int i = 0; i < selectedCombos.size(); i++) {
                Combo combo = selectedCombos.get(i);
                comboNames.append(combo.getName()).append(", ");
                totalQuantity += quantities.get(i); // Accumulate quantity
            }

            // Remove the last comma and space if they exist
            if (comboNames.length() > 0) {
                comboNames.setLength(comboNames.length() - 2);
            }
            String formattedTotal = df.format(total);
            // Set values for the insert statement
            pstmtInsert.setString(1, currentMovieName);
            pstmtInsert.setString(2, currentRoomName);
            pstmtInsert.setString(3, currentBookingDate);
            pstmtInsert.setString(4, currentBookingTime);
            pstmtInsert.setString(5, getSelectedSeatsString());
            pstmtInsert.setInt(6, memberId);
            pstmtInsert.setInt(7, selectedSeats.size());
            pstmtInsert.setDouble(8, total);
            pstmtInsert.setString(9, comboNames.toString());
            pstmtInsert.setInt(10, existingQualityFood + totalQuantity); // Add existing quantity to new total
            pstmtInsert.setString(11, voucherId); // Insert voucher ID

            pstmtInsert.executeUpdate();

            ResultSet generatedKeys = pstmtInsert.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String getSelectedCombosString() {
        StringBuilder comboList = new StringBuilder();
        for (Combo combo : selectedCombos) {
            comboList.append(combo.getName()).append(", "); // Append each combo's name
        }
        // Remove the last comma and space if there are any combos selected
        return comboList.length() > 0 ? comboList.substring(0, comboList.length() - 2) : "None";
    }

    private void insertPaymentRecord(int bookingId, double amount, String paymentMethod) {
        String sql = "INSERT INTO tbl_payments (booking_id, amount, payment_method, payment_status, payment_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, paymentMethod);
            pstmt.setString(4, "Completed");
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức tính tổng giá trị của các combo đã đặt
    private double calculateTotalPrice() {
        double total = 0;
        // Tính tổng từ các ghế đã chọn
        for (Seat seat : selectedSeats) {
            total += getTicketPrice(seat.name);
        }
        for (int i = 0; i < quantities.size(); i++) {
            total += quantities.get(i) * prices.get(i);
        }
        return total;
    }

    private double getTicketPrice(String seatName) {
        String sql = "SELECT ticket_price FROM tbl_seats WHERE seat_number = ? AND id_showcalendar = ?";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, seatName);
            pstmt.setInt(2, currentShowCalendarId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ticket_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getSelectedSeatsString() {
        StringBuilder seatList = new StringBuilder();
        for (Seat seat : selectedSeats) {
            seatList.append(seat.name).append(", ");
        }
        return seatList.length() > 0 ? seatList.substring(0, seatList.length() - 2) : "None";
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private boolean validateCustomerInfo(String name, String phone, String email) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showError(null, "All fields are required.");
            return false;
        }
        return true;
    }

    private void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String[] getMovies() {
        ArrayList<String> movies = new ArrayList<>();
        String sql = "SELECT name FROM tbl_movie";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                movies.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies.toArray(new String[0]);
    }

    private String[] getRooms() {
        ArrayList<String> rooms = new ArrayList<>();
        String sql = "SELECT name FROM tbl_room";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                rooms.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms.toArray(new String[0]);
    }

    private ArrayList<Combo> getSelectedCombos() {
        return new ArrayList<>(selectedCombos); // Return a copy of the selected combos
    }

    private ArrayList<Integer> getComboQuantities() {
        return new ArrayList<>(quantities); // Return a copy of the quantities
    }

    private String[] getShowDates() {
        ArrayList<String> dates = new ArrayList<>();
        String sql = "SELECT DISTINCT show_date FROM tbl_showcalendar";
        try (Connection connection = DBConnect.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                dates.add(rs.getString("show_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates.toArray(new String[0]);
    }

    public class Combo {

        private String name; // Combo name
        private double price; // Combo price

        // Constructor
        public Combo(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Setter for name
        public void setName(String name) {
            this.name = name;
        }

        // Getter for price
        public double getPrice() {
            return price;
        }

        // Setter for price
        public void setPrice(double price) {
            this.price = price;
        }
    }

    public class Seat {

        private String name; // This can be the seat number or identifier

        // Constructor
        public Seat(String name) {
            this.name = name;
        }

        // Getter method for name
        public String getName() {
            return name;
        }

        // Optionally, you can add other relevant methods, like equals and hashCode
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Seat)) {
                return false;
            }
            Seat other = (Seat) obj;
            return name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

    }

    class MutableDouble {

        double value;

        MutableDouble(double value) {
            this.value = value;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
