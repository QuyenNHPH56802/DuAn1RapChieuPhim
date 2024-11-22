/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelMember;
import com.project.utils.DBConnect;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Respository_Member {
    private String sql = null;
    public ArrayList <ModelMember> getAll(){
        ArrayList<ModelMember> lists = new ArrayList<>();
        sql = """
             SELECT [id]
                   ,[tai_khoan]
                   ,[mat_khau]
                   ,[role]
               FROM [dbo].[Users]
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelMember clas = new ModelMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lists.add(clas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public ModelMember getID(int id){
        ModelMember lists = null;
        sql = """
             SELECT [id]
                   ,[tai_khoan]
                   ,[mat_khau]
                   ,[role]
               FROM [dbo].[Users] where id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lists = new ModelMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    
    public  ArrayList<ModelMember> timTen( String name){
        ArrayList<ModelMember> list_students = new ArrayList<>();
        sql = """
             SELECT [id]
                                  ,[tai_khoan]
                                  ,[mat_khau]
                                  ,[role]
                              FROM [dbo].[Users] where tai_khoan like ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, "%" +name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               ModelMember teacher = new ModelMember(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list_students.add(teacher);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public int update(String id , ModelMember st){
        sql = """
              update [dbo].[Users] set tai_khoan =?, mat_khau= ?, role = ? where name = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            
            ps.setObject(1, st.getTaiKhoan());
            ps.setObject(2, st.getMatKhau());
            ps.setObject(3, st.getRole());
           
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelMember st){
        sql = """
             INSERT INTO [dbo].[Users]
                        ([tai_khoan]
                        ,[mat_khau]
                        ,[role])
                    values (?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
           
            ps.setObject(1, st.getTaiKhoan());
            ps.setObject(2, st.getMatKhau());
            ps.setObject(3, st.getRole());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              DELETE FROM [dbo].[Users] where id = ?
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
