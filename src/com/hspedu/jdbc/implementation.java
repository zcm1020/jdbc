package com.hspedu.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings("all")
public class implementation {
    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String ename = scanner.nextLine();
//        String pwd = scanner.nextLine();
//        String oldename = scanner.nextLine();
        //驱动 and 链接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        Connection connection = DriverManager.getConnection(url, user, password);

        //组织sql
//        String sql = "insert into admin values(?,?)";
//        String sql = "delete from admin where ename = ?";
//        String sql = "update admin set ename = ? where ename = ?";
        String sql = "select * from admin";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, ename);
//        preparedStatement.setString(2, oldename);
//        int i = preparedStatement.executeUpdate();
//        System.out.println(i > 0 ? "执行成功" : "执行失败");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String string = resultSet.getString(1);
            String string1 = resultSet.getString(2);
            System.out.println(string + "\t" + string1);
        }

        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
