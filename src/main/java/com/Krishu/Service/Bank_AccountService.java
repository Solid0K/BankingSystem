package com.Krishu.Service;

import com.Krishu.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank_AccountService {
    private static Connection connection;
    private static PreparedStatement statement;
    public static double checkBalance(int account_id) throws SQLException {
        connection= DBConnection.getConnection();
        String sql="select balance from bank_account where account_id=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,account_id);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                return set.getDouble("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static boolean AccountExist(int account_id) throws SQLException {
        connection=DBConnection.getConnection();
        String sql="select * from bank_account where account_id=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,account_id);
            ResultSet set=statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
