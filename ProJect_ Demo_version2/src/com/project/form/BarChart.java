/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.project.form;

import com.project.utils.DBConnect;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author admin
 */
public class BarChart extends javax.swing.JPanel {
     private JPanel dailyChartPanel;
    private JPanel monthlyChartPanel;
    private JPanel additionalChartPanel; // Third chart panel
    private JPanel controlPanel;
    
    private JComboBox<String> chartTypeComboBox;
    private JPanel mainChartPanel;
    private java.sql.Date selectedDate;
    /**
     * Creates new form BarChart
     */
    

public BarChart() {
    initComponents();
    setLayout(new BorderLayout());

    // Create panels for charts
    dailyChartPanel = new JPanel(new BorderLayout());
    monthlyChartPanel = new JPanel(new BorderLayout());
    additionalChartPanel = new JPanel(new BorderLayout());
    controlPanel = new JPanel();

    // Initialize JComboBox
    String[] chartTypes = {"Today", "Daily", "Monthly", "Yearly"};
    chartTypeComboBox = new JComboBox<>(chartTypes);
    chartTypeComboBox.addActionListener((ActionListener) new ChartTypeComboBoxListener ());

    // Add components to control panel
    controlPanel.add(chartTypeComboBox);
    

    // Initialize mainChartPanel with CardLayout
    mainChartPanel = new JPanel(new CardLayout());
    
    mainChartPanel.add(dailyChartPanel, "Daily");
    mainChartPanel.add(monthlyChartPanel, "Monthly");
    mainChartPanel.add(additionalChartPanel, "Yearly");

    // Add mainChartPanel and controlPanel to the frame
    add(mainChartPanel, BorderLayout.CENTER);
    add(controlPanel, BorderLayout.SOUTH);

    // Show today's chart by default
    showTodayChart(); // Display today's revenue initially
    // No need to show monthly or yearly charts at startup

    // Control buttons
    JButton dateButton = new JButton("Select Date");
    dateButton.addActionListener(e -> showDateChooser());
    controlPanel.add(dateButton);
}


// Listener for JComboBox
private class ChartTypeComboBoxListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String selectedType = (String) chartTypeComboBox.getSelectedItem();
        CardLayout cl = (CardLayout) mainChartPanel.getLayout();
        
        if (selectedType != null) {
            switch (selectedType) {
                case "Today":
                    showTodayChart(); // Show today's revenue
                    cl.show(mainChartPanel, "Daily");
                    break;
                case "Daily":
    if (selectedDate != null) {
        showDailyChart(selectedDate); // Show revenue for the selected date
    } else {
        // If selectedDate is null, show today's chart
        showTodayChart(); // Show today's revenue by default
    }
    cl.show(mainChartPanel, "Daily");
    break;
                case "Monthly":
                    String yearInput = JOptionPane.showInputDialog(BarChart.this, "Enter Year:");
                    if (yearInput != null) {
                        int year = Integer.parseInt(yearInput);
                        showMonthlyChart(year); // Show monthly revenue for the specified year
                        cl.show(mainChartPanel, "Monthly");
                    }
break;
                case "Yearly":
                    showAdditionalChart(); // Show yearly revenue
                    cl.show(mainChartPanel, "Yearly");
                    break;
            }
        }
    }
}
   private void showDateChooser() {
    JFrame dateFrame = new JFrame("Select a Date");
    dateFrame.setSize(300, 200);
    dateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JDateChooser dateChooser = new JDateChooser();
    dateFrame.add(dateChooser, BorderLayout.CENTER);

    JButton selectButton = new JButton("Select");
    selectButton.addActionListener(e -> {
        java.util.Date date = dateChooser.getDate();
        if (date != null) {
            selectedDate = new java.sql.Date(date.getTime());
            showDailyChart(selectedDate); // Display revenue for the selected date
        }
        dateFrame.dispose(); // Close date chooser
    });

    dateFrame.add(selectButton, BorderLayout.SOUTH);
    dateFrame.setVisible(true);
}
public void showTodayChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    LocalDate today = LocalDate.now();
    int month = today.getMonthValue();
    int year = today.getYear();
    int day = today.getDayOfMonth();

    String sql = "SELECT SUM(total) AS total_income FROM tbl_bookings WHERE DAY(booking_date) = ? AND MONTH(booking_date) = ? AND YEAR(booking_date) = ?";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, day);
        pstmt.setInt(2, month);
        pstmt.setInt(3, year);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            double totalIncome = rs.getDouble("total_income");
            dataset.setValue(totalIncome, "Income", String.valueOf(day));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    JFreeChart chart = ChartFactory.createBarChart(
            "Doanh thu của ngày " + day + "/" + month + "/" + year,
            "Day",
            "Tổng (VNĐ)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
    );

    customizeChart(chart);
    
    dailyChartPanel.removeAll(); // Clear previous chart

    // Create a ChartPanel and set its preferred size
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(200, 300)); // Adjust width as necessary

    dailyChartPanel.add(chartPanel, BorderLayout.CENTER); // Add the chart panel
    dailyChartPanel.revalidate(); // Refresh the panel
    dailyChartPanel.repaint(); // Repaint the panel
}

    public void showDailyChart(java.sql.Date date) {
    if (date == null) {
        // Handle the case where date is null (e.g., show an error message)
        System.out.println("Date is null, cannot show daily chart.");
        return; // Exit the method if date is null
    }
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    LocalDate localDate = date.toLocalDate(); // This line can cause a NullPointerException if date is null
    int month = localDate.getMonthValue();
    int year = localDate.getYear();

    int daysInMonth = localDate.lengthOfMonth();
    double[] dailyIncome = new double[daysInMonth];

    String sql = "SELECT DAY(booking_date) AS day, SUM(total) AS total_income " +
            "FROM tbl_bookings " +
            "WHERE MONTH(booking_date) = ? AND YEAR(booking_date) = ? " +
            "GROUP BY DAY(booking_date) ORDER BY DAY(booking_date)";

    try (Connection connection = DBConnect.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, month);
        pstmt.setInt(2, year);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int day = rs.getInt("day");
            double totalIncome = rs.getDouble("total_income");
            dailyIncome[day - 1] = totalIncome;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    for (int i = 0; i < dailyIncome.length; i++) {
        dataset.setValue(dailyIncome[i], "Income", String.valueOf(i + 1));
    }

    JFreeChart chart = ChartFactory.createBarChart(
            "Doanh thu hằng ngày của tháng " + month + "/" + year,
            "Day",
            "Tổng (VNĐ)",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
    );

    customizeChart(chart);
    dailyChartPanel.removeAll();
    dailyChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
    dailyChartPanel.revalidate();
    dailyChartPanel.repaint();
}
    

    public void showMonthlyChart() {
        String yearInput = JOptionPane.showInputDialog(this, "Enter Year:");
        if (yearInput != null) {
            int year = Integer.parseInt(yearInput);
            showMonthlyChart(year);
        }
    }

    public void showMonthlyChart(int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT MONTH(booking_date) AS month, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE YEAR(booking_date) = ? " +
                "GROUP BY MONTH(booking_date) ORDER BY MONTH(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();

            double[] monthlyIncome = new double[12];
            while (rs.next()) {
                int month = rs.getInt("month");
                double totalIncome = rs.getDouble("total_income");
                monthlyIncome[month - 1] = totalIncome;
            }
            for (int i = 0; i < monthlyIncome.length; i++) {
dataset.setValue(monthlyIncome[i], "Income", String.valueOf(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo tháng trong năm " + year,
                "Month",
                "Tổng (VNĐ)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart);
        monthlyChartPanel.removeAll();
        monthlyChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        monthlyChartPanel.revalidate();
        monthlyChartPanel.repaint();
    }

    public void showAdditionalChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int currentYear = LocalDate.now().getYear();
        int startYear = 2020;

        for (int year = startYear; year <= currentYear; year++) {
            dataset.setValue(0, "Annual Income", String.valueOf(year));
        }

        String sql = "SELECT YEAR(booking_date) AS year, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE YEAR(booking_date) BETWEEN ? AND ? " +
                "GROUP BY YEAR(booking_date) " +
                "ORDER BY YEAR(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, startYear);
            pstmt.setInt(2, currentYear);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int year = rs.getInt("year");
                double totalIncome = rs.getDouble("total_income");
                dataset.setValue(totalIncome, "Annual Income", String.valueOf(year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo năm",
                "Year",
                "Tổng (VNĐ)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart);
        additionalChartPanel.removeAll();
        additionalChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        additionalChartPanel.revalidate();
        additionalChartPanel.repaint();
    }

    public void customizeChart(JFreeChart chart) {
        chart.setBackgroundPaint(new Color(245, 245, 245)); // Light gray for modern look
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 24));
        chart.getTitle().setPaint(new Color(45, 45, 45)); // Elegant dark gray
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // Clean white background
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);
plot.setOutlineVisible(false);
        
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 16));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 16));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setTickLabelPaint(new Color(70, 70, 70));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(true);
        renderer.setShadowPaint(new Color(200, 200, 200));

        // Gradient for bars
        for (int i = 0; i < plot.getDataset().getRowCount(); i++) {
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(60, 120, 240),
                0, 0, new Color(120, 180, 255));
            renderer.setSeriesPaint(i, gradient);
        }

        // Tooltips
        for (int i = 0; i < plot.getDataset().getRowCount(); i++) {
            renderer.setSeriesToolTipGenerator(i, new StandardCategoryToolTipGenerator(
                "{0}: {1} = {2}", NumberFormat.getCurrencyInstance()));
        }

        // Legend customization
        LegendTitle legend = chart.getLegend();
        if (legend != null) {
            legend.setPosition(RectangleEdge.BOTTOM);
            legend.setItemFont(new Font("SansSerif", Font.PLAIN, 14));
            legend.setBackgroundPaint(new Color(240, 240, 240));
            legend.setFrame(BlockBorder.NONE);
        }

        // Annotations (optional)
        double maxValue = 0;
        String bestCategory = "";
        for (int i = 0; i < plot.getDataset().getColumnCount(); i++) {
            Number value = plot.getDataset().getValue(0, i);
            if (value != null && value.doubleValue() > maxValue) {
                maxValue = value.doubleValue();
                bestCategory = plot.getDataset().getColumnKey(i).toString();
            }
        }

        if (!bestCategory.isEmpty()) {
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
            renderer.setDefaultItemLabelPaint(Color.BLACK);
            renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        }
    }

    private void compareRevenue() {
    String[] options = {"Days", "Months", "Years"};
    String selectedOption = (String) JOptionPane.showInputDialog(this, "Chọn kiểu so sánh:", "Compare Revenue",
            JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    if (selectedOption != null) {
        switch (selectedOption) {
            case "Days":
                compareRevenueByDays();
                break;
            case "Months":
                compareRevenueByMonths();
                break;
            case "Years":
compareRevenueByYears();
                break;
        }
    } else {
        // Nếu không có lựa chọn, hiển thị doanh số cho ngày hiện tại
        LocalDate today = LocalDate.now();
        showDailyChart(java.sql.Date.valueOf(today));
    }
}

    private void compareRevenueByDays() {
        JDateChooser startDateChooser = new JDateChooser();
        JDateChooser endDateChooser = new JDateChooser();
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Start Date:"));
        panel.add(startDateChooser);
        panel.add(new JLabel("End Date:"));
        panel.add(endDateChooser);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select Date Range for Comparison", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            java.util.Date startDate = startDateChooser.getDate();
            java.util.Date endDate = endDateChooser.getDate();
            if (startDate != null && endDate != null) {
                compareDailyIncome(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
            } else {
                JOptionPane.showMessageDialog(this, "Please select valid dates.");
            }
        }
    }

    private void compareDailyIncome(java.sql.Date startDate, java.sql.Date endDate) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT DAY(booking_date) AS day, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE booking_date BETWEEN ? AND ? " +
                "GROUP BY DAY(booking_date) ORDER BY DAY(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int day = rs.getInt("day");
                double totalIncome = rs.getDouble("total_income");
                dataset.setValue(totalIncome, "Income", String.valueOf(day));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Daily Income Comparison",
                "Day",
                "Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart);
        dailyChartPanel.removeAll();
        dailyChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        dailyChartPanel.revalidate();
        dailyChartPanel.repaint();
    }

    private void compareRevenueByMonths() {
        String yearInput1 = JOptionPane.showInputDialog(this, "Enter First Year:");
        String yearInput2 = JOptionPane.showInputDialog(this, "Enter Second Year:");
if (yearInput1 != null && yearInput2 != null) {
            try {
                int year1 = Integer.parseInt(yearInput1);
                int year2 = Integer.parseInt(yearInput2);
                compareMonthlyIncome(year1, year2);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid year format.");
            }
        }
    }

    private void compareMonthlyIncome(int year1, int year2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT MONTH(booking_date) AS month, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE YEAR(booking_date) = ? OR YEAR(booking_date) = ? " +
                "GROUP BY MONTH(booking_date) ORDER BY MONTH(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, year1);
            pstmt.setInt(2, year2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("month");
                double totalIncome = rs.getDouble("total_income");
                dataset.setValue(totalIncome, "Year " + year1, String.valueOf(month));
                dataset.setValue(totalIncome, "Year " + year2, String.valueOf(month));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Income Comparison",
                "Month",
                "Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart);
        monthlyChartPanel.removeAll();
        monthlyChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        monthlyChartPanel.revalidate();
        monthlyChartPanel.repaint();
    }

    private void compareRevenueByYears() {
        String yearInput1 = JOptionPane.showInputDialog(this, "Nhập vào năm:");
        String yearInput2 = JOptionPane.showInputDialog(this, "Nhập vào năm:");
        if (yearInput1 != null && yearInput2 != null) {
            try {
                int year1 = Integer.parseInt(yearInput1);
                int year2 = Integer.parseInt(yearInput2);
                compareAnnualIncome(year1, year2);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid year format.");
            }
        }
    }

    private void compareAnnualIncome(int year1, int year2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT YEAR(booking_date) AS year, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE YEAR(booking_date) = ? OR YEAR(booking_date) = ?" +
                "GROUP BY YEAR(booking_date)";
try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, year1);
            pstmt.setInt(2, year2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int year = rs.getInt("year");
                double totalIncome = rs.getDouble("total_income");
                dataset.setValue(totalIncome, "Year " + year, String.valueOf(year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Annual Income Comparison",
                "Year",
                "Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart);
        additionalChartPanel.removeAll();
        additionalChartPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        additionalChartPanel.revalidate();
        additionalChartPanel.repaint();
    }

    private void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Income Data");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Type");
                headerRow.createCell(1).setCellValue("Amount");

                // Populate data from all three charts
                populateExcelData(sheet);

                // Write the output to a file
                try (FileOutputStream fileOut = new FileOutputStream(fileToSave.getAbsolutePath() + ".xlsx")) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(this, "Excel file has been exported successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting to Excel.");
            }
        }
    }

    private void populateExcelData(Sheet sheet) {
        int rowCount = 1; // Start from the second row

        // Daily Income Data
        double[] dailyIncome = getDailyIncomeData();
        for (int day = 0; day < dailyIncome.length; day++) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue("Daily Income Day " + (day + 1));
            row.createCell(1).setCellValue(dailyIncome[day]);
        }

        // Monthly Income Data
        double[] monthlyIncome = getMonthlyIncomeData();
for (int month = 0; month < monthlyIncome.length; month++) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue("Monthly Income Month " + (month + 1));
            row.createCell(1).setCellValue(monthlyIncome[month]);
        }

        // Annual Income Data
        double[] annualIncome = getAnnualIncomeData();
        for (int year = 0; year < annualIncome.length; year++) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue("Annual Income Year " + (2020 + year));
            row.createCell(1).setCellValue(annualIncome[year]);
        }
    }

    private double[] getDailyIncomeData() {
        double[] dailyIncome = new double[31]; // Assuming max 31 days
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();

        String sql = "SELECT DAY(booking_date) AS day, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "WHERE MONTH(booking_date) = ? AND YEAR(booking_date) = ? " +
                "GROUP BY DAY(booking_date) ORDER BY DAY(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int day = rs.getInt("day");
                double totalIncome = rs.getDouble("total_income");
                dailyIncome[day - 1] = totalIncome; // Store income for the corresponding day
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dailyIncome;
    }

    private double[] getMonthlyIncomeData() {
        double[] monthlyIncome = new double[12]; // 12 months
        String sql = "SELECT MONTH(booking_date) AS month, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
                "GROUP BY MONTH(booking_date) ORDER BY MONTH(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("month");
                double totalIncome = rs.getDouble("total_income");
                monthlyIncome[month - 1] = totalIncome; // Store income for the corresponding month
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monthlyIncome;
    }

    private double[] getAnnualIncomeData() {
        int currentYear = LocalDate.now().getYear();
        double[] annualIncome = new double[currentYear - 2019]; // From 2020 to current year
        String sql = "SELECT YEAR(booking_date) AS year, SUM(total) AS total_income " +
                "FROM tbl_bookings " +
"WHERE YEAR(booking_date) >= 2020 " +
                "GROUP BY YEAR(booking_date) ORDER BY YEAR(booking_date)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int year = rs.getInt("year");
                double totalIncome = rs.getDouble("total_income");
                annualIncome[year - 2020] = totalIncome; // Store income for the corresponding year
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return annualIncome;
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
