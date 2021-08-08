package Util;

import java.sql.*;

public class DBUtil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "123456";

    public static Connection open(){
        try{
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
