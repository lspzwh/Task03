package Student;

public class Student {
    private int id;
    private int Math;
    private int English;
    private int physic;
    public Student(int id ,int Math,int English,int physic){
        this.English=English;
        this.id=id;
        this.Math=Math;
        this.physic=physic;
    }

    public int getEnglish() {
        return English;
    }

    public int getId() {
        return id;
    }

    public int getMath() {
        return Math;
    }

    public int getPhysic() {
        return physic;
    }

    public void setEnglish(int english) {
        English = english;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMath(int math) {
        Math = math;
    }

    public void setPhysic(int physic) {
        this.physic = physic;
    }
}
