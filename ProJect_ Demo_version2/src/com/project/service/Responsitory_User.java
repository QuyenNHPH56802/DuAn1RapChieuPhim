
package com.project.service;
import com.project.model.ModelMember;
import com.project.utils.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class Responsitory_User {
    private String sql = null;
     String SELECT_BY_ID_SQL = "SELECT [id]\n" +
"      ,[tai_khoan]\n" +
"      ,[mat_khau]\n" +
"      ,[role]\n" +
"  FROM [dbo].[Users] WHERE tai_khoan= ?";
    public ModelMember getByID(String tai_khoan) {
        try {
            try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL);) {
                ps.setObject(1, tai_khoan);
                try (ResultSet rs = ps.executeQuery();) {
                    List<ModelMember> list = new ArrayList<>();
                    while (rs.next()) {
                       ModelMember x = new ModelMember();
                        x.setId(rs.getInt("id"));
                        x.setTaiKhoan(rs.getString("tai_khoan"));
                        x.setMatKhau(rs.getString("mat_khau"));
                        x.setRole(rs.getString("role"));
                        return x;
                    }
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
