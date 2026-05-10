package com.Krishu.Service;

import com.Krishu.DB.DBConnection;
import com.Krishu.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private static Connection connection;
    private static PreparedStatement statement;

    public static boolean register(String UserName,String Email,String passWord) throws SQLException {
        String sql="insert into users(full_name,email,password) values(?,?,?)";
        connection= DBConnection.getConnection();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,UserName);
            statement.setString(2,Email);
            statement.setString(3,passWord);
            int row= statement.executeUpdate();
            return row != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ChangePassword(String email,String NewPassWord) throws SQLException {
        String sql="update into users set password=? where email=?";
        connection= DBConnection.getConnection();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,NewPassWord);
            statement.setString(2,email);
            int row= statement.executeUpdate();
            return row!=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean CheckingEmail(String email) throws SQLException {
        String sql="select * from users where email=?";
        connection= DBConnection.getConnection();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet set=statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
