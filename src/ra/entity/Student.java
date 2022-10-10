package ra.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements IStudentManagement {
    private String studentID;
    private String studentName;
    private int age;
    private boolean sex;
    private StudentClass studentClass;
    private List<Float> listMarkJS = new ArrayList<>();
    private List<Float> listMarkJavaCore = new ArrayList<>();
    private List<Float> listMarkJavaWeb = new ArrayList<>();
    private float avgMark;
    private String gpa;
    private boolean studentStatus;

    public Student() {
    }

    public Student(String studentID, String studentName, int age, boolean sex, StudentClass studentClass, List<Float> listMarkJS, List<Float> listMarkJavaCore, List<Float> listMarkJavaWeb, float avgMark, String gpa, boolean studentStatus) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.sex = sex;
        this.studentClass = studentClass;
        this.listMarkJS = listMarkJS;
        this.listMarkJavaCore = listMarkJavaCore;
        this.listMarkJavaWeb = listMarkJavaWeb;
        this.avgMark = avgMark;
        this.gpa = gpa;
        this.studentStatus = studentStatus;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public List<Float> getListMarkJS() {
        return listMarkJS;
    }

    public void setListMarkJS(List<Float> listMarkJS) {
        this.listMarkJS = listMarkJS;
    }

    public List<Float> getListMarkJavaCore() {
        return listMarkJavaCore;
    }

    public void setListMarkJavaCore(List<Float> listMarkJavaCore) {
        this.listMarkJavaCore = listMarkJavaCore;
    }

    public List<Float> getListMarkJavaWeb() {
        return listMarkJavaWeb;
    }

    public void setListMarkJavaWeb(List<Float> listMarkJavaWeb) {
        this.listMarkJavaWeb = listMarkJavaWeb;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public boolean isStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Input Student ID: ");
        do {
            this.studentID = scanner.nextLine();
            if (this.studentID.trim().length()==6){
                if (this.studentID.trim().charAt(0)=='S'){
                    break;
                }else {
                    System.err.println("First character must be 'J'");
                }
            }else {
                System.err.println("Student ID include 6 character");
            }
        }while (true);
        System.out.println("Input Student name: ");
        do {
            this.studentName = scanner.nextLine();
            if (this.studentName.trim().length()>6&&this.studentName.trim().length()<=50){
                break;
            }else {
                System.err.println("6-30 character");
            }
        }while (true);
        System.out.println("Input Student age: ");
        do {
            this.age = Integer.parseInt(scanner.nextLine());
            if (this.age>=18){
                break;
            }else {
                System.err.println("Student age must >= 18");
            }
        }while (true);
        System.out.println("Input Student sex: ");
        this.sex = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Input JS point: ");
        inputMark(listMarkJS, scanner);
        System.out.println("Input Java Core Point: ");
        inputMark(listMarkJavaCore, scanner);
        System.out.println("Input Java Web point: ");
        inputMark(listMarkJavaWeb, scanner);
        System.out.println("Student Status: ");
        this.studentStatus = Boolean.parseBoolean(scanner.nextLine());

    }
    public static void inputMark(List<Float> listMark, Scanner scanner){
        int count = 1;
        do {
            System.out.printf("Input your point %d: ",count);
            listMark.add(Float.parseFloat(scanner.nextLine()));
            count++;
            System.out.println("Do you want to input more point?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.println("Your choice?");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice!=1){
                break;
            }
        }while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Student ID: %s - Name: %s - Age: %d - Gender: %b - Class: %s\n",this.studentID,this.studentName,this.age,this.sex,this.studentClass.getClassName());
        System.out.print("JS Point: ");
        for (Float js_mark : listMarkJS) {
            System.out.printf("%f\t", js_mark);
        }
        System.out.println();
        System.out.println("Java Core Point: ");
        for (Float js_mark : listMarkJavaCore) {
            System.out.printf("%f\t", js_mark);
        }
        System.out.println();
        System.out.println("Java Web Point: ");
        for (Float js_mark : listMarkJavaWeb) {
            System.out.printf("%f\t", js_mark);
        }
        System.out.println();
        System.out.printf("Mark average: %f - Rank: %s - Status: %b\n", this.avgMark,this.gpa,this.studentStatus);
    }
    public void calAvgMark(){
        this.avgMark = (listMarkJS.get(listMarkJS.size()-1)+listMarkJavaCore.get(listMarkJavaCore.size()-1)+listMarkJavaWeb.get(listMarkJavaWeb.size()-1))/3;
    }
    public void studentRank(){
        if (this.avgMark<5){
            this.gpa = "Weak";
        } else if (this.avgMark < 7) {
            this.gpa = "Normal";
        } else if (this.avgMark < 9) {
            this.gpa = "Good";
        }else {
            this.gpa = "Excellent";
        }
    }
}
