package ra.entity;

import java.util.Scanner;


public class StudentClass implements IStudentManagement{
    private String classID;
    private String className;
    private String descriptions;
    private int classStatus;

    public StudentClass() {
    }

    public StudentClass(String classID, String className, String descriptions, int classStatus) {
        this.classID = classID;
        this.className = className;
        this.descriptions = descriptions;
        this.classStatus = classStatus;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(int classStatus) {
        this.classStatus = classStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Enter Class ID: ");
        do {
            System.err.println("Must have 'J' at the first char and have 5 char");
                this.classID = scanner.nextLine();
        }while (!(this.classID.charAt(0)=='J'&&this.classID.trim().length()==5));
        System.out.println("Enter Class Name: ");
        do {
            System.err.println("Max char of class name is 10");
            this.className = scanner.nextLine();
        }while (!(this.className.length()>0&&this.className.trim().length()<=10));
        System.out.println("Descriptions: ");
        this.descriptions = scanner.nextLine();
        System.out.println("Class Status: ");
        this.classStatus = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("%-15s%-20s%-20s%-15d\n", this.classID, this.className, this.descriptions, this.classStatus);
    }
}
