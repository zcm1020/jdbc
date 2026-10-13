import com.hspedu.jdbc.JDBCUtilesDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        int Fid = Integer.parseInt(scanner.nextLine());
        int Tid = Integer.parseInt(scanner.nextLine());

        String sql = "update account set balance = balance - 1000 where id = ?";
        String sql2 = "update account set balance = balance + 1000 where id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtilesDruid.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Fid);
            preparedStatement.executeUpdate();
            int i = 1 / 0;
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,Tid);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {

            try {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            //关闭
            JDBCUtilesDruid.close(null,preparedStatement,connection);
        }



    }
}