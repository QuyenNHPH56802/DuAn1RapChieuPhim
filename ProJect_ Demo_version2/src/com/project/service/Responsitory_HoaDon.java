/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelHoaDon;
import com.project.utils.DBConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Responsitory_HoaDon {
    private String sql = null;
    public ArrayList<ModelHoaDon> getAll(){
        ArrayList<ModelHoaDon> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[movie_name]
                    ,[room]
                    ,[booking_date]
                    ,[booking_time]
                    ,[seat_ids]
                    ,[member_id]
                    ,[quantity]
                    ,[total]
                    ,[status]
                    ,[voucher_id]
              ,[time_at]
                    ,[combo_food]
                    ,[quality_food]
                FROM [dbo].[tbl_bookings]
              """;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp showDate = rs.getTimestamp("time_at");
                 Timestamp showTime = rs.getTimestamp("booking_time");
                String format1 = timeFormat.format(showTime);
                String format = timeFormat.format(showDate);
                ModelHoaDon diemdanh = new ModelHoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), format1, rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getString(10), rs.getInt(11),format,rs.getString(13),rs.getInt(14));
                lists.add(diemdanh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
  
     public ArrayList<ModelHoaDon> getID( int id){
        ArrayList<ModelHoaDon> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                     ,[movie_name]
                     ,[room]
                     ,[booking_date]
                     ,[booking_time]
                     ,[seat_ids]
                     ,[member_id]
                     ,[quantity]
                     ,[total]
                     ,[status]
                     ,[voucher_id]
                 FROM [dbo].[tbl_bookings] where member_id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelHoaDon diemdanh = new  ModelHoaDon(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getDouble(9),rs.getString(10),rs.getInt(11));
                lists.add(diemdanh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public ModelHoaDon getIDCan( int id){
        ModelHoaDon hoadon = null;
        sql = """
              SELECT [id]
                    ,[movie_name]
                    ,[room]
                    ,[booking_date]
                    ,[booking_time]
                    ,[seat_ids]
                    ,[member_id]
                    ,[quantity]
                    ,[total]
                    ,[status]
                    ,[voucher_id]
                 FROM [dbo].[tbl_bookings] where member_id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hoadon = new  ModelHoaDon(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getDouble(9),rs.getString(10),rs.getInt(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoadon;
    }
      public  ArrayList<ModelHoaDon> timTen( int name){
        ArrayList<ModelHoaDon> list_students = new ArrayList<>();
        sql = """
             SELECT [id]
                   ,[movie_name]
                   ,[room]
                   ,[booking_date]
                   ,[booking_time]
                   ,[seat_ids]
                   ,[member_id]
                   ,[quantity]
                   ,[total]
                   ,[status]
                   ,[voucher_id]
                               FROM [dbo].[tbl_bookings] where member_id = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, name );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               ModelHoaDon teacher = new ModelHoaDon(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getDouble(9),rs.getString(10),rs.getInt(11));
                list_students.add(teacher);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public int update(int id , ModelHoaDon st){
        sql = """
              update [dbo].[tbl_bookings] set movie_name =?, room= ?,booking_date = ?,booking_time = ?,member_id = ?,total =?,status =?,voucher_id =? where id = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            
            ps.setObject(1, st.getMovieName());
            ps.setObject(2, st.getRoom());
            ps.setObject(3, st.getThoiGianThanhToan());
            ps.setObject(4, st.getGioThanhToan());
            ps.setObject(5, st.getSeats_id());
            ps.setObject(6, st.getMemberID());
            ps.setObject(7, st.getQuaility());
            ps.setObject(8, st.getTotal());
            ps.setObject(9, st.getStatus());
            ps.setObject(10, st.getVoucherID());
           
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelHoaDon st){
        sql = """
             INSERT INTO [dbo].[tbl_bookings]
                        ([movie_name]
                        ,[room]
                        ,[booking_date]
                        ,[booking_time]
                        ,[seat_ids]
                        ,[member_id]
                        ,[quantity]
                        ,[total]
                        ,[status]
                        ,[voucher_id])
                    values (?,?,?,?,?,?,?,?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
           
            ps.setObject(1, st.getMovieName());
            ps.setObject(2, st.getRoom());
            ps.setObject(3, st.getThoiGianThanhToan());
            ps.setObject(4, st.getGioThanhToan());
            ps.setObject(5, st.getSeats_id());
            ps.setObject(6, st.getMemberID());
            ps.setObject(7, st.getQuaility());
            ps.setObject(8, st.getTotal());
            ps.setObject(9, st.getStatus());
            ps.setObject(10, st.getVoucherID());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              DELETE [dbo].[tbl_bookings] FROM  where id = ?
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
