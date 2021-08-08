package Test4;

import java.sql.Date;

public class Student {
    int id;
    String name;
    Date birth;
    String sex;
    public Student(int id ,String name,Date birth,String sex){
        this.id=id;
        this.name=name;
        this.birth=birth;
        this.sex=sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
