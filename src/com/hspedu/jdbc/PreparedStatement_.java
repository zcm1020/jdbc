package com.hspedu.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings("all")
public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名和密码：");
        String ename = scanner.nextLine();
        String pwd = scanner.nextLine();

        //驱动和链接问题
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Connection connection = DriverManager.getConnection(url, user, password);

        //组织sql问题，使用perparedStatement来进行sql语句通信
        String sql = "select * from admin where ename = ? and pwd = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,ename);
        preparedStatement.setString(2,pwd);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String string = resultSet.getString(1);
            String string1 = resultSet.getString(2);
            System.out.println(string + "\t" + string1);
        }

//        if (resultSet.next()){
//            System.out.println("登录成功");
//        }else {
//            System.out.println("登录失败");
//        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
