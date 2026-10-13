package com.hspedu.jdbc;

import jdk.nashorn.internal.ir.CallNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestJDBC_CRUD {
    public static void main(String[] args) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("输入名字和余额");
//        String ename  = scanner.nextLine();
//        int balance = scanner.nextInt();
//        int id = scanner.nextInt();

        //建立连接
        Connection connection = JDBCUtilesDruid.getConnection();
        //组织sql
//        String sql = "insert into account(ename,balance) values(?,?)";
//        String sql2 = "update account set ename = ? where id = ?";
//        String sql3 = "delete from account where id = ?";
        String sql4 = "select * from account";
        PreparedStatement preparedStatement = connection.prepareStatement(sql4);
//        preparedStatement.setString(1,ename);
//        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int anInt = resultSet.getInt("id");
            String string = resultSet.getString("ename");
            int anInt1 = resultSet.getInt("balance");
            System.out.println(anInt + "\t" + string + "\t" + anInt1);
        }
//        int i = preparedStatement.executeUpdate();
//        System.out.println(i > 0? "执行成功" : "执行失败");

        JDBCUtilesDruid.close(resultSet,preparedStatement,connection);
    }
}
