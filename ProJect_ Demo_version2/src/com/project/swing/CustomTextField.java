package com.project.swing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField(int columns) {
        super(columns);
        customize();
    }

    private void customize() {
        // Tùy chỉnh viền
        Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
        setBorder(border);

        // Tùy chỉnh màu nền
        setBackground(Color.LIGHT_GRAY);

        // Tùy chỉnh font chữ
        setFont(new Font("Arial", Font.PLAIN, 14));

        // Tùy chỉnh kích thước
        setPreferredSize(new Dimension(250, 30));
    }
}