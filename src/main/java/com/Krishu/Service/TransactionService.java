package com.Krishu.Service;

import com.Krishu.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {
    private static Connection connection;
    private static PreparedStatement statement;

    public static boolean Deposit(int account_id,double amount) throws SQLException {
        connection= DBConnection.getConnection();
        try{
            if(Bank_AccountService.AccountExist(account_id)){
                String sql="update bank_account set balance=balance+? where account_id=?";
                statement=connection.prepareStatement(sql);
                statement.setDouble(1,amount);
                statement.setInt(2,account_id);
                statement.executeUpdate();
                String sql1="insert into transactions(receiver_account,amount,transaction_type) values(?,?,?)";
                String transaction_type="Deposit";
                statement=connection.prepareStatement(sql1);
                statement.setInt(1,account_id);
                statement.setDouble(2,amount);
                statement.setString(3,transaction_type);
                int rowsAffected=statement.executeUpdate();
                return rowsAffected != 0;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean WithDraw(int account_id,double amount) throws SQLException {
        connection=DBConnection.getConnection();
        try{
            if(Bank_AccountService.AccountExist(account_id) && Bank_AccountService.checkBalance(account_id)>=amount){
                String sql="update bank_account set balance=balance-? where account_id=?";
                statement=connection.prepareStatement(sql);
                statement.setDouble(1,amount);
                statement.setInt(2,account_id);
                statement.executeUpdate();
                String sql0="insert into transactions(sender_account,amount,transaction_type) values(?,?,?)";
                String transaction_type="Withdraw";
                statement=connection.prepareStatement(sql0);
                statement.setInt(1,account_id);
                statement.setDouble(2,amount);
                statement.setString(3,transaction_type);
                int rowAffected=statement.executeUpdate();
                return rowAffected!=0;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean Transfer(int s_account,int r_account,double amount) throws SQLException {
        connection=DBConnection.getConnection();
        try{
            if(Bank_AccountService.AccountExist(s_account) && Bank_AccountService.AccountExist(r_account)){
                if(Bank_AccountService.checkBalance(s_account)>=amount){
                    String sql1="update bank_account set balance=balance-? where account_id=?";
                    String sql2="update bank_account set balance=balance+? where account_id=?";
                    String sql3="insert into transactions(sender_account,receiver_account,amount,transaction_type) values(?,?,?,?)";
                    String transaction_type="Transfer";
                    statement= connection.prepareStatement(sql1);
                    statement.setDouble(1,amount);
                    statement.setInt(2,s_account);
                    statement.executeUpdate();
                    statement= connection.prepareStatement(sql2);
                    statement.setDouble(1,amount);
                    statement.setInt(2,r_account);
                    statement.executeUpdate();
                    statement=connection.prepareStatement(sql3);
                    statement.setInt(1,s_account);
                    statement.setInt(2,r_account);
                    statement.setDouble(3,amount);
                    statement.setString(4,transaction_type);
                    int rowAffected=statement.executeUpdate();
                    return rowAffected!=0;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
