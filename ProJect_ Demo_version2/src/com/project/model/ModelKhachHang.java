/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelKhachHang {
    private int id;
    private String hoTen;
   private String email;
   private String sdt;

    public ModelKhachHang() {
    }

    public ModelKhachHang(int id, String hoTen, String email, String sdt) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
    }

    public ModelKhachHang(String hoTen, String email, String sdt) {
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

   
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getHoTen(),this.getEmail(),this.getSdt()
        };
    }
}
