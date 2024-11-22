/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.service;

import com.project.model.Model_Payment;
import com.project.utils.DBConnect;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class Respository_Payment {
    String sql = null;
    public ArrayList<Model_Payment> getAll(){
        ArrayList<Model_Payment> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[booking_id]
                    ,[amount]
                    ,[payment_method]
                    ,[payment_status]
                    ,[payment_date]
                FROM [dbo].[tbl_payments]
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Model_Payment diemdanh = new Model_Payment(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                lists.add(diemdanh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    public ArrayList<Model_Payment> getID( int id){
        ArrayList<Model_Payment> lists = new ArrayList<>();
        sql = """
              SELECT [id]
                    ,[booking_id]
                    ,[amount]
                    ,[payment_method]
                    ,[payment_status]
                    ,[payment_date]
                FROM [dbo].[tbl_payments] where id = ?
              """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)){
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Model_Payment diemdanh = new Model_Payment(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6));
                lists.add(diemdanh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
    
}
