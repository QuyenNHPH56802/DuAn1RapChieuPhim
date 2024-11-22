/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class Movie {
       String title;
    String posterPath;
    String videoPath; // Đường dẫn video chính
    String trailerPath; // Đường dẫn trailer

    public Movie(String title, String posterPath, String videoPath) {
        this.title = title;
        this.posterPath = posterPath;
        this.videoPath = videoPath; // Khởi tạo đường dẫn video chính
        this.trailerPath = videoPath; // Giả sử đây là đường dẫn trailer (có thể thay đổi)
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }
    
}
