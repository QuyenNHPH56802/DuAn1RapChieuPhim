/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;
import java.sql.Time;
import java.sql.Timestamp;
/**
 *
 * @author admin
 */
public class ModelHoaDon {
    private int id;
    private String movieName;
    private String room;
    private String thoiGianThanhToan;
    private String gioThanhToan;
    private String seats_id;
    private int memberID;
    private int quaility;
    private double total;
    private String status;
    private int voucherID;
    private String time;
    private String nameCombo;
    private Integer soLuongCombo;

    public ModelHoaDon() {
    }

    public ModelHoaDon(int id, String movieName, String room, String thoiGianThanhToan, String gioThanhToan, String seats_id, int memberID, int quaility, double total, String status, int voucherID) {
        this.id = id;
        this.movieName = movieName;
        this.room = room;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.gioThanhToan = gioThanhToan;
        this.seats_id = seats_id;
        this.memberID = memberID;
        this.quaility = quaility;
        this.total = total;
        this.status = status;
        this.voucherID = voucherID;
    }

   
    
    public ModelHoaDon(String movieName, String room, String thoiGianThanhToan, String gioThanhToan, String seats_id, int memberID, int quaility, double total, String status, int voucherID, String time) {
        this.movieName = movieName;
        this.room = room;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.gioThanhToan = gioThanhToan;
        this.seats_id = seats_id;
        this.memberID = memberID;
        this.quaility = quaility;
        this.total = total;
        this.status = status;
        this.voucherID = voucherID;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ModelHoaDon(int id, String movieName, String room, String thoiGianThanhToan, String gioThanhToan, String seats_id, int memberID, int quaility, double total, String status, int voucherID, String time, String nameCombo, Integer soLuongCombo) {
        this.id = id;
        this.movieName = movieName;
        this.room = room;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.gioThanhToan = gioThanhToan;
        this.seats_id = seats_id;
        this.memberID = memberID;
        this.quaility = quaility;
        this.total = total;
        this.status = status;
        this.voucherID = voucherID;
        this.time = time;
        this.nameCombo = nameCombo;
        this.soLuongCombo = soLuongCombo;
    }

    public String getNameCombo() {
        return nameCombo;
    }

    public void setNameCombo(String nameCombo) {
        this.nameCombo = nameCombo;
    }

    public Integer getSoLuongCombo() {
        return soLuongCombo;
    }

    public void setSoLuongCombo(Integer soLuongCombo) {
        this.soLuongCombo = soLuongCombo;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(String thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public String getGioThanhToan() {
        return gioThanhToan;
    }

    public void setGioThanhToan(String gioThanhToan) {
        this.gioThanhToan = gioThanhToan;
    }

    public String getSeats_id() {
        return seats_id;
    }

    public void setSeats_id(String seats_id) {
        this.seats_id = seats_id;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getQuaility() {
        return quaility;
    }

    public void setQuaility(int quaility) {
        this.quaility = quaility;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    

    
  
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getId(),this.getMovieName(),this.getRoom(),this.getThoiGianThanhToan(),this.getGioThanhToan(),this.getSeats_id(),this.getMemberID(),this.getQuaility(),this.getTotal(),this.getStatus(),this.getVoucherID(),this.getTime(),this.getNameCombo(),this.getSoLuongCombo()
        };
     
    }
}
