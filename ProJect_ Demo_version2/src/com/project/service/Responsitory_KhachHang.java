/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelKhachHang;
import com.project.utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Responsitory_KhachHang {
    String sql = null;
    public ArrayList<ModelKhachHang> getAll(){
        ArrayList<ModelKhachHang> list = new ArrayList<>();
        sql = """
             SELECT  [id]
                    ,[name]
                    ,[phone]
                    ,[email]
                FROM [movieticketbookingtest].[dbo].[tbl_members]
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelKhachHang teacher = new ModelKhachHang(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                list.add(teacher);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ModelKhachHang getID(int id){
        ModelKhachHang lists = null;
        sql = """
             SELECT [id]
                                  ,[name]
                                  ,[phone]
                                  ,[email]
                              FROM [dbo].[tbl_members] where id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lists = new ModelKhachHang(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public  ArrayList<ModelKhachHang> timTen( String name){
        ArrayList<ModelKhachHang> list_students = new ArrayList<>();
        sql = """
             SELECT [id]
                                  ,[name]
                                  ,[phone]
                                  ,[email]
                              FROM [dbo].[tbl_members] where phone like ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, "%" +name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               ModelKhachHang teacher = new ModelKhachHang(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                list_students.add(teacher);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public int update(String id , ModelKhachHang st){
        sql = """
              update [dbo].[tbl_members] set name =?, phone= ?, email = ? where name = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            
            ps.setObject(1, st.getHoTen());
            ps.setObject(2, st.getSdt());
            ps.setObject(3, st.getEmail());
           
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelKhachHang st){
        sql = """
             INSERT INTO [dbo].[tbl_members]
                         ([name]
                         ,[phone]
                         ,[email])
                    values (?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
           
            ps.setObject(1, st.getHoTen());
            ps.setObject(2, st.getSdt());
            ps.setObject(3, st.getEmail());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              DELETE FROM [dbo].[tbl_members] where id = ?
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
