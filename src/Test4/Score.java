package Test4;

public class Score {
    int id;
    int courseId;
    int grade;
    public Score(int id,int courseId,int grade){
        this.courseId=courseId;
        this.grade=grade;
        this.id=id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
