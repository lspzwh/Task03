package Test4;

import Util.DBUtil;

import java.sql.*;
import java.util.*;

public class Task {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "123456";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,username,password);
            stmt = conn.createStatement();
            createStudent(stmt);
            createCourse(stmt);
            createScore(stmt);
            createTeacher(stmt);
            findMaxMin(stmt);
            studentSort(stmt);
            average(stmt);
            stmt.close();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void createStudent(Statement stmt){
        String sql="CREATE TABLE IF NOT EXISTS student " +
                "(id int(10) not NULL, " +
                " name char(10), " +
                " birth date, " +
                " sex char(4), " +
                " PRIMARY KEY ( id ))";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void createScore(Statement stmt){
        String sql="CREATE TABLE IF NOT EXISTS score " +
                "(id int(10) not NULL, " +
                " courseId int(20), " +
                " grade int(4))" ;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void createCourse(Statement stmt){
        String sql="CREATE TABLE IF NOT EXISTS course " +
                "(courseId int(20) not NULL, " +
                " name char(16), " +
                " teacherId int(10)) ";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void createTeacher(Statement stmt){
        String sql="CREATE TABLE IF NOT EXISTS teacher " +
                "(teacherId int(20) not NULL, " +
                " name char(10)) " ;

        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //找课程最高分最低分
    public  static void findMaxMin(Statement stmt){
        Map<Integer,Sc> map=new HashMap<Integer, Sc>();
        Sc sc;
        try{
            ResultSet resultSet=stmt.executeQuery("select * from score");
            while (resultSet.next()){
                if(!map.containsKey(resultSet.getInt("courseId"))){
                    sc=new Sc(resultSet.getInt("grade"),resultSet.getInt("grade"));
                    map.put(resultSet.getInt("courseId"),sc);
                }else{
                    sc=map.get(resultSet.getInt("courseId"));
                    int grade=resultSet.getInt("grade");
                    if(grade> sc.max){
                        sc.max=grade;
                        map.put(resultSet.getInt("courseId"),sc);
                    }else if(grade<sc.min){
                        sc.min=grade;
                        map.put(resultSet.getInt("courseId"),sc);
                    }
                }
            }
            for(Integer x:map.keySet()){
                System.out.println("课程"+x+"\n最高分:"+map.get(x).max+"\n最低分:"+map.get(x).min);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //给学生排名
    public static  void studentSort(Statement stmt){
        Map<Integer,Integer> map=new HashMap<>();
        try {
            ResultSet resultSet=stmt.executeQuery("select * from score");
            while(resultSet.next()){
                map.put(resultSet.getInt("id"),resultSet.getInt("grade")
                        +map.getOrDefault(resultSet.getInt("id"),0));
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            Task.ValueComparator vc=new ValueComparator();
            Collections.sort(list,vc);
            for (Map.Entry<Integer, Integer> integerIntegerEntry : list) {
                System.out.println(integerIntegerEntry);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //平均分
    public static void average(Statement stmt){
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,Integer> times=new HashMap<>();
        try {
            ResultSet resultSet=stmt.executeQuery("select * from score");
            while(resultSet.next()){
                map.put(resultSet.getInt("id"),resultSet.getInt("grade")
                        +map.getOrDefault(resultSet.getInt("id"),0));
                times.put(resultSet.getInt("id"),
                        1 +times.getOrDefault(resultSet.getInt("id"),0));
            }
            for(Integer key:map.keySet()){
                if(map.get(key)/times.get(key)>=60){
                    System.out.println(key+"的平均分:"+(double)map.get(key)/times.get(key));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //插入分数
    public static void insertScore(Score score){
        Connection connection= DBUtil.open();
        String sql="INSERT INTO score(id,courseId,grade) VALUES(?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,score.id);
            preparedStatement.setInt(2,score.courseId);
            preparedStatement.setInt(3,score.grade);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection);
        }
    }
    private static class ValueComparator implements Comparator<Map.Entry<Integer,Integer>>
    {
        public int compare(Map.Entry<Integer,Integer> m,Map.Entry<Integer,Integer> n)
        {
            return n.getValue()-m.getValue();
        }
    }
}

class Sc{
    int max=0;
    int min=100;
    public Sc(int max, int min){
        this.max=max;
        this.min=min;
    }
}
