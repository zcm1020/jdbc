package com.hspedu.jdbc;

import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc01 {
    public static void main(String[] args) throws SQLException, IOException {
        //1.注册驱动
//        Driver driver = new Driver();
//        //2.建立连接
//        String url = "jdbc:mysql://localhost:3306/hsp_db02?useUnicode=true&characterEncoding=UTF-8";
//        Properties properties = new Properties();
//        properties.setProperty("user","root");
//        properties.setProperty("password","hsp");
//        Connection connect = driver.connect(url, properties);

        //第二种链接
//        String url = "jdbc:mysql://localhost:3306/hsp_db02?useUnicode=true&characterEncoding=UTF-8";
//        String user = "root";
//        String password = "hsp";
//        Connection connect = DriverManager.getConnection(url, user, password);

        //第三种链接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));//通过配置文件链接

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Connection connect = DriverManager.getConnection(url, user, password);


        //3.执行操作
        String sql = "insert into actor values(3,'天王李','1997-06-06')";
//        String sql = "update actor set ename = '小兵张嘎' where id = 2";
//        String sql = "insert into actor values(4,'王菲','1997-06-06')";
//        String sql = "insert into actor values(5,'李连成','1997-06-06')";
//        String sql = "delete from actor where id = 3";
        Statement statement = connect.createStatement();
        int i = statement.executeUpdate(sql); //受影响的行数
        System.out.println(i > 0?"成功":"失败");
        //4.关闭链接
        statement.close();
        connect.close();

    }
}
