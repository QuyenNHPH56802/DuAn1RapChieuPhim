/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelGhe;
import com.project.utils.DBConnect;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Respository_Ghe {
    private String sql = null;
    public ArrayList<ModelGhe> getAll(){
        ArrayList<ModelGhe> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[id_showcalendar]
                    ,[seat_number]
                    ,[member_id]
                    ,[status]
                    ,[reserved_until]
                    ,[seat_type]
                    ,[ticket_price]
                FROM [dbo].[tbl_seats]
              """;
        DecimalFormat df = new DecimalFormat("#,###");
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                
                String formatPrint = df.format(rs.getDouble(8)) + " (VND)";
                ModelGhe study = new ModelGhe(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),formatPrint);
                lists.add(study);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
     public ArrayList<ModelGhe> getByID(int id){
        ArrayList<ModelGhe> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                                  ,[id_showcalendar]
                                  ,[seat_number]
                                  ,[member_id]
                                  ,[status]
                                  ,[reserved_until]
                                  ,[seat_type]
                                  ,[ticket_price]
                              FROM [dbo].[tbl_seats]
               where id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelGhe study = new ModelGhe(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                lists.add(study);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
     public  ArrayList<ModelGhe> timMa( String id){
        ArrayList<ModelGhe> list_students = new ArrayList<>();
        sql = """
              SELECT [id]
                                   ,[id_showcalendar]
                                   ,[seat_number]
                                   ,[member_id]
                                   ,[status]
                                   ,[reserved_until]
                                   ,[seat_type]
                                   ,[ticket_price]
                               FROM [dbo].[tbl_seats] where seat_type = ? 
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, "%" +id + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelGhe st = new ModelGhe(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDouble(8));
                list_students.add(st);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public int update(int id , ModelGhe st){
        sql = """
              update [dbo].[tbl_seats] set id_showcalendar =?, seat_number= ?, member_id = ?,status =? ,reserved_until = ?, seat_type = ?,ticket_price =? where id_showcalendar = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, st.getId_lichChieu());
            ps.setObject(2, st.getSoGhe());
            ps.setObject(3, st.getKhach_hang_id());
            ps.setObject(4, st.getTrangThai());
           ps.setObject(5, st.getThoigian());
           ps.setObject(6, st.getLoaiGhe());
           ps.setObject(7, st.getGiaVe());
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelGhe st){
        sql = """
             INSERT INTO [dbo].[tbl_seats]
                         ([id_showcalendar]
                         ,[seat_number]
                         ,[member_id]
                         ,[status]
                         ,[reserved_until]
                         ,[seat_type]
                         ,[ticket_price]) values (?,?,?,?,?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
            ps.setObject(1, st.getId_lichChieu());
            ps.setObject(2, st.getSoGhe());
            ps.setObject(3, st.getKhach_hang_id());
            ps.setObject(4, st.getTrangThai());
           ps.setObject(5, st.getThoigian());
           ps.setObject(6, st.getLoaiGhe());
           ps.setObject(7, st.getGiaVe());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              delete [dbo].[tbl_seats] where id_showcalendar = ?
              """;
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
