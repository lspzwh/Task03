package Test4;

public class Course {
    int courseId;
    String name;
    int teacherId;
    public Course(int courseId,String name,int teacherId){
        this.courseId=courseId;
        this.name=name;
        this.teacherId=teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
