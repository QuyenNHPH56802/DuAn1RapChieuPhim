/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelListFilm {
    private int id;
    private String hoten;
    private int thoiGian;
    private String daoDien;
    private String moTa;

    public ModelListFilm() {
    }

    public ModelListFilm(int id, String hoten, int thoiGian, String daoDien, String moTa) {
        this.id = id;
        this.hoten = hoten;
        this.thoiGian = thoiGian;
        this.daoDien = daoDien;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

   

  
   
   
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getHoten(),this.getThoiGian(),this.getDaoDien(),this.getMoTa()
        };
    }
}
