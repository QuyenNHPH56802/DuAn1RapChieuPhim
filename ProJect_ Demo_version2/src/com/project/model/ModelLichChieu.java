/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelLichChieu {
    private int id;
    private int phim_id;
    private int room_id;
    private String ngay_Chieu;
    private String thoiGianBatDau;
    
    

    public ModelLichChieu() {
    }

    public ModelLichChieu(int id, int phim_id, int room_id, String ngay_Chieu, String thoiGianBatDau) {
        this.id = id;
        this.phim_id = phim_id;
        this.room_id = room_id;
        this.ngay_Chieu = ngay_Chieu;
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public ModelLichChieu(int phim_id, int room_id, String ngay_Chieu, String thoiGianBatDau) {
        this.phim_id = phim_id;
        this.room_id = room_id;
        this.ngay_Chieu = ngay_Chieu;
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhim_id() {
        return phim_id;
    }

    public void setPhim_id(int phim_id) {
        this.phim_id = phim_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getNgay_Chieu() {
        return ngay_Chieu;
    }

    public void setNgay_Chieu(String ngay_Chieu) {
        this.ngay_Chieu = ngay_Chieu;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getPhim_id(),this.getRoom_id(),this.getNgay_Chieu(),this.getThoiGianBatDau()
        };
    }
}
