/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;
import java.sql.*;
import com.project.model.ModelDatVe;
import com.project.utils.DBConnect;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Respository_DatVe {
    private String sql = null;
    public ArrayList<ModelDatVe> getAll(){
            ArrayList<ModelDatVe> lists = new ArrayList<>();
    sql = """
          SELECT [id]
                ,[ten]
                ,[gia]
            FROM [dbo].[LoaiVe]
          """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModelDatVe diem = new ModelDatVe(rs.getInt(1), rs.getString(2), rs.getInt(3));
                lists.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
       
}
