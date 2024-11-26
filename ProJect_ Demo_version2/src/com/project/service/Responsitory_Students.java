/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import com.project.model.ModelListFilm;
import com.project.utils.DBConnect;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Responsitory_Students {
   private String sql = null;
   
    public  ArrayList<ModelListFilm> getAll(){
        ArrayList<ModelListFilm> list_students = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[name]
                    ,[duration]
                    ,[director]
                    ,[description]
                FROM [dbo].[tbl_movie]
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
    ModelListFilm st = new ModelListFilm(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getString(5));
    list_students.add(st);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_students;
    }
    public ArrayList<ModelListFilm> timMa(String query) {
    ArrayList<ModelListFilm> listFilms = new ArrayList<>();
    // Câu truy vấn tìm kiếm theo ID, tên hoặc mô tả
    sql = """
          SELECT [id]
                 ,[name]
                 ,[duration]
                 ,[director]
                 ,[description]
          FROM [dbo].[tbl_movie]
          WHERE name LIKE ? OR id LIKE ? OR description LIKE ?
          """;

    try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        
        String searchParam = "%" + query + "%";
        ps.setString(1, searchParam); 
        ps.setString(2, searchParam); 
        ps.setString(3, searchParam); 
        ps.setString(4, searchParam);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModelListFilm film = new ModelListFilm(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            listFilms.add(film);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listFilms;
}
    public int update(String id , ModelListFilm st){
        sql = """
              update Phim set name =?, duration= ?, director = ?,description =? where name = ?
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, st.getHoten());
            ps.setObject(3, st.getDaoDien());
            ps.setObject(4, st.getMoTa());
            ps.setObject(2, st.getThoiGian());
           
           
            ps.setObject(6, st.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int add(ModelListFilm st){
        sql = """
             insert into tbl_movie(name,duration,director,description) values (?,?,?,?)
              """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)){
           
            ps.setObject(1, st.getHoten());
            ps.setObject(3, st.getDaoDien());
            ps.setObject(4, st.getMoTa());
            ps.setObject(2, st.getThoiGian());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
           
        } return 0;
    }
    public int delete( int id){
        sql = """
              delete tbl_movie where id = ?
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

