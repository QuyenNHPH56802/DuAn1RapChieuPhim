/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trailer_Video;

/**
 *
 * @author admin
 */
public class Practice {
    public Practice() {
        // Đường dẫn đến tệp thực thi của VLC (hoặc Windows Media Player)
        String playerPath = "C:\\Program Files\\VideoLAN\\VLC\\vlc.exe"; // Thay đổi đường dẫn nếu cần
        // Đường dẫn đến tệp video MP4
        String videoPath = "C:\\Users\\Admin\\Videos\\ASMRZ(TANAKA, NEEDMORECASH) - 잘자요 아가씨(prod. Gwana)❤️ Official Music Video.mp4"; // Thay đổi đường dẫn đến video của bạn

        try {
            // Tạo đối tượng ProcessBuilder để khởi động trình phát video với tệp video
            ProcessBuilder processBuilder = new ProcessBuilder(playerPath, videoPath);
            processBuilder.start(); // Khởi động video
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }
    public static void main(String[] args) {
        new Practice();
    }
}

