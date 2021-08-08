package Test4;

public class Teacher {
    int teacherId;
    String name;
    public Teacher(int teacherId,String name){
        this.name=name;
        this.teacherId=teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }
}
