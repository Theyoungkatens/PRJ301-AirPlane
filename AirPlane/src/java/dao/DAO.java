/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Products;
import dto.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Zane
 */
public class DAO {

    private static final String LOGIN = "SELECT userID, fullName, roleID FROM tblUsers WHERE userID = ? AND password= ?";
    private static final String SEARCH = "SELECT [id],[name] ,[description] ,[quantity] FROM [dbo].[tblProducts] WHERE [id] LIKE ? ORDER BY  [quantity] ASC";
    private static final String UPDATE = "UPDATE [dbo].[tblProducts] SET [name] = ?, [description] = ?, [quantity] = ? WHERE [id] LIKE ?";
    private static final String DELETE = "DELETE [dbo].[tblProducts] WHERE [id] = ?";

    public Users checkLogin(String userID, String password) throws SQLException {
        Users user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(LOGIN);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new Users(userID, fullName, "", roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                rs.close();
            }
            if (con != null) {
                rs.close();
            }
        }
        return user;
    }

    public List<Products> getProductList(String search) throws SQLException, ClassNotFoundException {
        List<Products> proList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(SEARCH);
                ps.setString(1, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    proList.add(new Products(id, name, description, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                rs.close();
            }
            if (con != null) {
                rs.close();
            }
        }
        return proList;
    }

    public boolean update(Products pro) throws SQLException {
        boolean checkUpdate = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(UPDATE);
                ps.setString(1, pro.getName());
                ps.setString(2, pro.getDescription());
                ps.setInt(3, pro.getQuantity());
                ps.setString(4, pro.getId());
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return checkUpdate;
    }

     public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean checkDelete = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(DELETE);
                ps.setString(1, id);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return checkDelete;
    }

}
