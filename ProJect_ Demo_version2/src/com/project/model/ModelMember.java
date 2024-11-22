/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

/**
 *
 * @author admin
 */
public class ModelMember {
    private int id;
    private String taiKhoan;
    private String matKhau;
    private String Role;

    public ModelMember() {
    }

    public ModelMember(int id, String taiKhoan, String matKhau, String Role) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.Role = Role;
    }

    public ModelMember(String taiKhoan, String matKhau, String Role) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.Role = Role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    

   
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getTaiKhoan(),this.getMatKhau(),this.getRole()
        };
    }
}
