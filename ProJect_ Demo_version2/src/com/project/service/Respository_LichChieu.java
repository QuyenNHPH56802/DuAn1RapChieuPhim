/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelLichChieu;
import com.project.utils.DBConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Respository_LichChieu {
    private String sql = null;
    public ArrayList<ModelLichChieu> getAll(){
        ArrayList<ModelLichChieu> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[movie_id]
                    ,[room_id]
                    ,[show_date]
                    ,[show_time]
                FROM [dbo].[tbl_showcalendar]
              """;
          SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); 
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp showDate = rs.getTimestamp("show_time");
                String format = timeFormat.format(showDate);
                ModelLichChieu subjects = new ModelLichChieu(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),format);
                lists.add(subjects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public  ArrayList<ModelLichChieu> timMa( String id){
        ArrayList<ModelLichChieu> list_students = new ArrayList<>();
        sql = """
              SELECT [id]
                     ,[movie_id]
                     ,[room_id]
                     ,[show_date]
                     ,[show_time]
                 FROM [dbo].[tbl_showcalendar] where show_time like ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, "%" +id + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelLichChieu st = new  ModelLichChieu(rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                list_students.add(st);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public int update(String id , ModelLichChieu st){
        sql = """
             UPDATE [dbo].[tbl_showcalendar] set [movie_id] =?, [room_id]= ?, [show_date] = ?,[show_time]    =? where name = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, st.getPhim_id());
            ps.setObject(3, st.getRoom_id());
            ps.setObject(4, st.getNgay_Chieu());
            ps.setObject(2, st.getThoiGianBatDau());
           
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int updateAll(){
        sql = """
             UPDATE [tbl_showcalendar]
             SET [show_date] = CAST(GETDATE() AS DATE)
             WHERE [show_date] IS NOT NULL;
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelLichChieu st){
        sql = """
             INSERT INTO [dbo].[tbl_showcalendar]
                         ([movie_id]
                         ,[room_id]
                         ,[show_date]
                         ,[show_time]) values (?,?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
           ps.setObject(1, st.getPhim_id());
            ps.setObject(3, st.getRoom_id());
            ps.setObject(4, st.getNgay_Chieu());
            ps.setObject(2, st.getThoiGianBatDau());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              DELETE FROM [dbo].[tbl_showcalendar] where id = ?
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
