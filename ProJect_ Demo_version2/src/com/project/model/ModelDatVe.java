/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelDatVe {
    private int id;
    private String ten;
    private double diemKy1;
   

    public ModelDatVe() {
    }

    public ModelDatVe(int id, String ten, double diemKy1) {
        this.id = id;
        this.ten = ten;
        this.diemKy1 = diemKy1;
    }

    public ModelDatVe(String ten, double diemKy1) {
        this.ten = ten;
        this.diemKy1 = diemKy1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDiemKy1() {
        return diemKy1;
    }

    public void setDiemKy1(double diemKy1) {
        this.diemKy1 = diemKy1;
    }

    
        public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getTen(),this.getDiemKy1()
        };
    }
}
