/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelGhe {
    private int id;
    private int id_lichChieu;
    private String soGhe;
    private int khach_hang_id;
    private String trangThai;
    private String thoigian;
    private String loaiGhe;
    private double giaVe;
    private String giVe;

    public ModelGhe(int id, int id_lichChieu, String soGhe, int khach_hang_id, String trangThai, String thoigian, String loaiGhe, String giVe) {
        this.id = id;
        this.id_lichChieu = id_lichChieu;
        this.soGhe = soGhe;
        this.khach_hang_id = khach_hang_id;
        this.trangThai = trangThai;
        this.thoigian = thoigian;
        this.loaiGhe = loaiGhe;
        this.giVe = giVe;
    }

    public String getGiVe() {
        return giVe;
    }

    public void setGiVe(String giVe) {
        this.giVe = giVe;
    }
    

    public ModelGhe(int id, int id_lichChieu, String soGhe, int khach_hang_id, String trangThai, String thoigian, String loaiGhe, double giaVe) {
        this.id = id;
        this.id_lichChieu = id_lichChieu;
        this.soGhe = soGhe;
        this.khach_hang_id = khach_hang_id;
        this.trangThai = trangThai;
        this.thoigian = thoigian;
        this.loaiGhe = loaiGhe;
        this.giaVe = giaVe;
    }

    public ModelGhe(int id_lichChieu, String soGhe, int khach_hang_id, String trangThai, String thoigian, String loaiGhe, double giaVe) {
        this.id_lichChieu = id_lichChieu;
        this.soGhe = soGhe;
        this.khach_hang_id = khach_hang_id;
        this.trangThai = trangThai;
        this.thoigian = thoigian;
        this.loaiGhe = loaiGhe;
        this.giaVe = giaVe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lichChieu() {
        return id_lichChieu;
    }

    public void setId_lichChieu(int id_lichChieu) {
        this.id_lichChieu = id_lichChieu;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public int getKhach_hang_id() {
        return khach_hang_id;
    }

    public void setKhach_hang_id(int khach_hang_id) {
        this.khach_hang_id = khach_hang_id;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

   
   
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getId_lichChieu(),this.getSoGhe(),this.getKhach_hang_id(),this.getTrangThai(),this.getThoigian(),this.getLoaiGhe(),this.getGiVe()
        };
    }
}
