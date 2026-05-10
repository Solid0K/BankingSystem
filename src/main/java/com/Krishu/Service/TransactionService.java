package com.Krishu.Service;

import com.Krishu.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {
    private static Connection connection;
    private static PreparedStatement statement;

    public boolean Deposit(int account_id,double amount) throws SQLException {
        connection= DBConnection.getConnection();
        try{
            if(Bank_AccountService.AccountExist(account_id)){
                String sql="update bank_account set balance=balance+? where account_id=?";
                statement=connection.prepareStatement(sql);
                statement.setDouble(1,amount);
                statement.setInt(2,account_id);
                int rows=statement.executeUpdate();
                if(rows!=0){
                    sql="insert into transaction(receiver_account,amount,transaction_type) values(?,?,?)";
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
