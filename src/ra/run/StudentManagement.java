package ra.run;

import ra.entity.IStudentManagement;
import ra.entity.Student;
import ra.entity.StudentClass;

import java.util.*;
import java.util.stream.Stream;

public class StudentManagement {
    static List<StudentClass> listClass = new ArrayList<>();
    static List<Student> listStudent = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("***************SCHOOL MANAGEMENT****************");
            System.out.println("1.Classes Management");
            System.out.println("2.Students Management");
            System.out.println("3.Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagement.classManagement();
                    break;
                case 2:
                    StudentManagement.StudentsManagement();
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.err.println("Chose from 1 to 3");
            }
        } while (true);
    }

    public static void classManagement() {
        boolean exitClassManagement = true;
        do {
            System.out.println("*************CLASSES MANAGEMENT**************");
            System.out.println("1.Add new class");
            System.out.println("2.Edit class information");
            System.out.println("3.Display class information");
            System.out.println("4.Display class active status");
            System.out.println("5.Search classes by class name");
            System.out.println("6.Exit");
            int choice2 = Integer.parseInt(scanner.nextLine());
            switch (choice2) {
                case 1:
                    StudentManagement.inputClasses(scanner);
                    break;
                case 2:
                    StudentManagement.updateClas(scanner);
                case 3:
                    StudentManagement.displayClasses();
                    break;
                case 4:
                    StudentManagement.getClassesActive();
                    break;
                case 5:
                    StudentManagement.searchClass(scanner);
                    break;
                case 6:
                    exitClassManagement = false;
                    break;
                default:
                    System.out.println("Chose from 1 to 6");
            }
        } while (exitClassManagement);
    }

    public static void StudentsManagement() {
        boolean exitStudentsManagement = true;
        do {
            System.out.println("***************STUDENTS MANAGEMENT**************");
            System.out.println("1.Add new students");
            System.out.println("2.Edit students information");
            System.out.println("3.Display students information");
            System.out.println("4.Count Student Mark?");
            System.out.println("5.Students Rank");
            System.out.println("6.Sort students by GPA");
            System.out.println("7.Search Students by student name");
            System.out.println("8.Display Students rank");
            System.out.println("9.Display Students pass the subjects");
            System.out.println("10.Exit");
            int choice3 = Integer.parseInt(scanner.nextLine());
            switch (choice3) {
                case 1:
                    StudentManagement.inputListStudents(scanner);
                    break;
                case 2:
                    StudentManagement.editStudentInformation(scanner);
                    break;
                case 3:
                    StudentManagement.displayStudentsInformation();
                    break;
                case 4:
                    StudentManagement.studentsAveragePoint();
                    break;
                case 5:
                    StudentManagement.studentsRank();
                    break;
                case 6:
                    StudentManagement.sortStudentsByGpa();
                    break;
                case 7:
                    StudentManagement.searchStudentByName(scanner);
                    break;
                case 8:
                    StudentManagement.displayStudentsRank();
                    break;
                case 9:
                    StudentManagement.studentPassSubjects();
                    break;
                case 10:
                    exitStudentsManagement = false;
                    break;
                default:
                    System.out.println("Chose from 1 to 10");
            }
        } while (exitStudentsManagement);
    }

    public static void inputClasses(Scanner scanner) {
        System.out.println("Enter number of classes: ");
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            StudentClass studentClass = new StudentClass();
            studentClass.inputData(scanner);
            listClass.add(studentClass);
        }
        System.out.println("Add classes success");
    }

    public static void updateClas(Scanner scanner) {
        System.out.println("Enter Class ID you want to update: ");
        String classID = scanner.nextLine();
        boolean exitClass = false;
        for (StudentClass studentClass : listClass) {
            if (studentClass.getClassID().equals(classID)) {
                System.out.println("Enter a new class name to update: ");
                String className = scanner.nextLine();
                if (!Objects.equals(className, "") && className.length() != 0) {
                    studentClass.setClassName(className);
                }
                System.out.println("Update Description: ");
                String des = scanner.nextLine();
                if (!Objects.equals(des, "") && des.length() != 0) {
                    studentClass.setDescriptions(des);
                }
                System.out.println("Update status: ");
                String classStatus = scanner.nextLine();
                if (!Objects.equals(classStatus, "") && classStatus.length() != 0) {
                    studentClass.setClassStatus(Integer.parseInt(classStatus));
                }
                System.out.println("Update complete!");
                exitClass = true;
                break;
            }
        }
        if (!exitClass) {
            System.out.println("404 Not Found");
        }
    }

    public static void displayClasses() {
        System.out.printf("%-15s%-20s%-20s%-15s\n", "Class ID", "Class Name", "Descriptions", "Class Status");
        for (StudentClass studentClass : listClass) {
            studentClass.displayData();
        }
    }

    public static void getClassesActive(){
        System.out.println("Classes is active: ");
        int countClassesActive = 0;
        for (StudentClass studentClass : listClass) {
            if (studentClass.getClassStatus()==0){
                studentClass.displayData();
                countClassesActive++;
            }
        }
        System.out.printf("%d Classes are active\n",countClassesActive);
    }

    public static void searchClass(Scanner scanner) {
        System.out.println("Enter class name you want to search: ");
        String search = scanner.nextLine();
        boolean exitClass = false;
        for (StudentClass studentClass : listClass) {
            if (studentClass.getClassName().contains(search)) {
                System.out.printf("%-15s%-20s%-20s%-15s\n", "Class ID", "Class Name", "Descriptions", "Class Status");
                studentClass.displayData();
                exitClass = true;
            }
        }
        if (!exitClass){
            System.out.println("404 Not Found");
        }
    }

    public static void inputListStudents(Scanner scanner) {
        System.out.println("How many students do you want to add?");
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            Student student = new Student();
            student.inputData(scanner);
            System.out.println("Which classes do you want to join?");
            int index = 1;
            for (StudentClass studentClass : listClass) {
                System.out.printf("%d,%s\n", index, studentClass.getClassName());
                index++;
            }
            System.out.println("Your Choice?");
            int choice = Integer.parseInt(scanner.nextLine());
            student.setStudentClass(listClass.get(choice - 1));
            listStudent.add(student);
        }
    }

    public static void editStudentInformation(Scanner scanner) {
        for (Student students : listStudent) {
            students.displayData();
        }
        System.out.println("Which Student ID do you want to edit?");
        String editStudent = scanner.nextLine();
        for (Student students : listStudent) {
            if (editStudent.equals(students.getStudentID())) {
                System.out.println("Update student name: ");
                String studentName = "";
                do {
                    studentName = scanner.nextLine();
                    if (studentName!=""&&studentName.length()!=0){
                        if (studentName.trim().length()>6&&studentName.trim().length()<=50){
                            students.setStudentName(studentName);
                            break;
                        }else {
                            System.out.println("6-30 character");
                        }
                    }
                }while (true);
                System.out.println("Update student age: ");
                String ageStudent = "";
                do {
                    ageStudent = scanner.nextLine();
                    if (ageStudent != "" && ageStudent.length()!=0) {
                        if (Integer.parseInt(ageStudent) >= 18) {
                            students.setAge(Integer.parseInt(ageStudent));
                            break;
                        }else {
                                System.err.println("Age must rather than 18");
                        }
                    }
                }while (true);

                System.out.println("Update student sex: ");
                String sex = scanner.nextLine();
                if (sex!="" && sex.length()!=0){
                    students.setSex(Boolean.parseBoolean(sex));
                }

                System.out.println("Update student class: ");
                int countClass = 1;
                for (StudentClass stuClass:listClass) {
                    System.out.printf("%d. %s\n",countClass,stuClass.getClassName());
                    countClass++;
                }
                System.out.printf("%d. Close update",countClass);
                System.out.println("Your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice != countClass){
                    students.setStudentClass(listClass.get(choice-1));
                }
                //Edit Score
                StudentManagement.updateStudentMark(students.getListMarkJS(), scanner, students,"JS");
                StudentManagement.updateStudentMark(students.getListMarkJavaCore(), scanner, students, "Java Core");
                StudentManagement.updateStudentMark(students.getListMarkJavaWeb(), scanner, students, "Java Web");
                //Tinh lai diem tb cho sv
                students.getAvgMark();
                //xep loai lai cho sv
                students.getGpa();
                // cap nhat lai trang thai
                System.out.println("Enter new student status: ");
                String studentStatus = scanner.nextLine();
                if (studentStatus!="" && studentStatus.length()!=0){
                    students.setStudentStatus(Boolean.parseBoolean(studentStatus));
                }
            }
        }
    }
    public static void updateStudentMark(List<Float> listMark, Scanner scanner, Student student, String subject){
        System.out.printf("Update score %s: \n",subject);
        System.out.println("1.Update all Score");
        System.out.println("2.Add new score");
        System.out.println("3.Update any score");
        System.out.println("4.Close");
        int choiceMark = Integer.parseInt(scanner.nextLine());
        switch (choiceMark){
            case 1:
                List<Float> list = new ArrayList<>();
                Student.inputMark(list, scanner);
                student.setListMarkJS(list);
                break;
            case 2:
                Student.inputMark(student.getListMarkJS(),scanner);
                break;
            case 3:
                System.out.println("Which exam do you want to edit?");
                int exam = Integer.parseInt(scanner.nextLine());
                System.out.println("Input a new score: ");
                float mark = Float.parseFloat(scanner.nextLine());
                student.getListMarkJS().set(exam - 1, mark);
                break;
        }
    }

    public static void displayStudentsInformation() {
        for (Student students : listStudent) {
            students.displayData();
        }
    }

    public static void studentsAveragePoint() {
        for (Student students : listStudent) {
            students.calAvgMark();
        }
        System.out.println("Finish Calculating");
    }

    public static void studentsRank() {
        for (Student students : listStudent) {
            students.studentRank();
        }
        System.out.println("Finish Ranking");
        System.out.println("Chose 3 to display Student List");
    }

    public static void sortStudentsByGpa() {
        listStudent.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o1.getAvgMark() - o2.getAvgMark());
            }
        });
        StudentManagement.displayStudentsInformation();
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Enter student name to search: ");
        String searchName = scanner.nextLine();
        boolean exitStudent = false;
        for (Student students : listStudent) {
            if (students.getStudentName().equals(searchName)) {
                students.displayData();
                exitStudent = true;
            }
        }
        if (!exitStudent){
            System.out.println("404 Not Found");
        }
    }

    public static void displayStudentsRank() {

        int countExcellent = 0;
        int countGood = 0;
        int countNormal = 0;
        int countWeak = 0;
        for (Student students : listStudent) {
            if (students.getGpa().equals("Excellent")) {
                countExcellent++;
            }
            if (students.getGpa().equals("Good")) {
                countGood++;
            }
            if (students.getGpa().equals("Normal")) {
                countNormal++;
            }
            if (students.getGpa().equals("Weak")) {
                countWeak++;
            }
        }
        System.out.printf("Excellent Students: %d students\n", countExcellent);
        System.out.printf("Good Students: %d students\n", countGood);
        System.out.printf("Normal Students: %d students\n", countNormal);
        System.out.printf("Weak Students: %d students\n", countWeak);
    }

    public static void studentPassSubjects() {
        System.out.println("Students pass the Subjects: ");
        for (Student students : listStudent) {
            int jsSize = students.getListMarkJS().size();
            int jcSize = students.getListMarkJavaCore().size();
            int jwSize = students.getListMarkJavaWeb().size();
            if (students.getAvgMark() >= IStudentManagement.MARK_PASS&& students.getListMarkJS().get(jsSize-1)>=5&&
            students.getListMarkJavaCore().get(jcSize-1)>=5&&students.getListMarkJavaWeb().get(jwSize-1)>=5) {
                System.out.printf("Student ID: %s - Student Name: %s - Class: %s - Mark AVG: %f\n", students.getStudentID(), students.getStudentName(), students.getStudentClass().getClassName(), students.getAvgMark());
            }
        }
    }
}
