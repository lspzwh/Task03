package Student;


import Util.DBUtil;

import java.sql.*;

public class Test {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "123456";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,username,password);
            stmt = conn.createStatement();
//            create(stmt);
            Student student=new Student(2,10,80,60);
            change(student);
            find_all(stmt);
            findLow(stmt);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    public static void create(Statement stmt){
        String sql="CREATE TABLE student1 " +
                "(id int(10) not NULL, " +
                " math int(4), " +
                " english int(4), " +
                " physical int(4), " +
                " PRIMARY KEY ( id ))";
        try {
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //查
    public static void find_all(Statement stmt){
        try {
            ResultSet resultSet = stmt.executeQuery("select * from student1");
            while (resultSet.next()) {
                System.out.println("数据库读取id：" + resultSet.getString("id"));
                System.out.println("数据库读取math：" + resultSet.getString("math"));
                System.out.println("数据库读取english：" + resultSet.getString("english"));
                System.out.println("数据库读取physical：" + resultSet.getString("physical"));
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //增
    public static void insert(Student student){
        Connection connection=DBUtil.open();
        String sql="INSERT INTO student1(id,math,english,physical) VALUES(?,?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,student.getId());
            preparedStatement.setObject(2,student.getMath());
            preparedStatement.setObject(3,student.getEnglish());
            preparedStatement.setObject(4,student.getPhysic());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection);
        }
    }
    //删
    public static void delete(int id){
        Connection connection=DBUtil.open();
        String sql="delete from student1 where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection);
        }
    }
    //改
    public static void change(Student student){
        Connection connection=DBUtil.open();
        String sql="UPDATE student1"+
                " set math=?, english=?, physical=?"+
                " where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getMath());
            preparedStatement.setInt(2,student.getEnglish());
            preparedStatement.setInt(3,student.getPhysic());
            preparedStatement.setInt(4,student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection);
        }
    }
    public static void findLow(Statement stmt){
        try {
            ResultSet resultSet = stmt.executeQuery("select * from student1");
            while (resultSet.next()) {
                if(resultSet.getInt("math")
                        +resultSet.getInt("english")
                        +resultSet.getInt("physical")<180){
                    System.out.println("不及格的有\n"+resultSet.getString("id"));
                }
            }

            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
