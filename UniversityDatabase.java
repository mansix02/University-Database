import java.util.*;

class Student {
    String name, rollNo, dob, gender, phoneNo, department;
    int semester;

    Student(String name, String rollNo, String dob, String gender, String phoneNo, int semester, String department) {
        this.name = name;
        this.rollNo = rollNo;
        this.dob = dob;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.semester = semester;
        this.department = department;
    }

    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNo + ", DOB: " + dob + ", Gender: " + gender +
                ", Phone No: " + phoneNo + ", Semester: " + semester + ", Department: " + department;
    }
}

class Teacher {
    String name, id, dob, gender, phoneNo, subject, department;

    Teacher(String name, String id, String dob, String gender, String phoneNo, String subject, String department) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.subject = subject;
        this.department = department;
    }

    public String toString() {
        return "Name: " + name + ", ID: " + id + ", DOB: " + dob + ", Gender: " + gender +
                ", Phone No: " + phoneNo + ", Subject: " + subject + ", Department: " + department;
    }
}

class Department {
    String name;
    int noOfStudents, noOfTeachers;

    Department(String name, int noOfStudents, int noOfTeachers) {
        this.name = name;
        this.noOfStudents = noOfStudents;
        this.noOfTeachers = noOfTeachers;
    }

    public String toString() {
        return "Department Name: " + name + ", No. of Students: " + noOfStudents + ", No. of Teachers: " + noOfTeachers;
    }
}

public class UniversityDatabase {
    static List<Student> students = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    static List<Department> departments = new ArrayList<>();

    public static void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Details: ");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Roll No: ");
        String rollNo = sc.nextLine();
        System.out.print("DOB: ");
        String dob = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Phone No: ");
        String phoneNo = sc.nextLine();
        System.out.print("Semester: ");
        int semester = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Department: ");
        String department = sc.nextLine();
        
        sc.nextLine();

        students.add(new Student(name, rollNo, dob, gender, phoneNo, semester, department));
        updateDepartmentStudentCount(department, 1);
    }

    public static void updateDepartmentStudentCount(String departmentName, int count) {
        for (Department dept : departments) {
            if (dept.name.equals(departmentName)) {
                dept.noOfStudents += count;
                return;
            }
        }
        departments.add(new Department(departmentName, count, 0));
    }

    public static void updateDepartmentTeacherCount(String departmentName, int count) {
        for (Department dept : departments) {
            if (dept.name.equals(departmentName)) {
                dept.noOfTeachers += count;
                return;
            }
        }
        departments.add(new Department(departmentName, 0, count));
    }

    public static void updateStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll No of student to update: ");
        String rollNo = sc.nextLine();
        for (Student student : students) {
            if (student.rollNo.equals(rollNo)) {
                System.out.print("New Name: ");
                student.name = sc.nextLine();
                System.out.print("New DOB: ");
                student.dob = sc.nextLine();
                System.out.print("New Gender: ");
                student.gender = sc.nextLine();
                System.out.print("New Phone No: ");
                student.phoneNo = sc.nextLine();
                System.out.print("New Semester: ");
                student.semester = sc.nextInt();
                sc.nextLine();  // Consume newline
                System.out.print("New Department: ");
                String newDept = sc.nextLine();

                if (!student.department.equals(newDept)) {
                    updateDepartmentStudentCount(student.department, -1);
                    student.department = newDept;
                    updateDepartmentStudentCount(newDept, 1);
                }

                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void searchStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll No to search: ");
        String rollNo = sc.nextLine();
        for (Student student : students) {
            if (student.rollNo.equals(rollNo)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Roll No of student to delete: ");
        String rollNo = sc.nextLine();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.rollNo.equals(rollNo)) {
                updateDepartmentStudentCount(student.department, -1);
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void addTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Teacher Details: ");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("DOB: ");
        String dob = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Phone No: ");
        String phoneNo = sc.nextLine();
        System.out.print("Subject: ");
        String subject = sc.nextLine();
        System.out.print("Department: ");
        String department = sc.nextLine();
        
        sc.nextLine();

        teachers.add(new Teacher(name, id, dob, gender, phoneNo, subject, department));
        updateDepartmentTeacherCount(department, 1);
    }

    public static void updateTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of teacher to update: ");
        String id = sc.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.id.equals(id)) {
                System.out.print("New Name: ");
                teacher.name = sc.nextLine();
                System.out.print("New DOB: ");
                teacher.dob = sc.nextLine();
                System.out.print("New Gender: ");
                teacher.gender = sc.nextLine();
                System.out.print("New Phone No: ");
                teacher.phoneNo = sc.nextLine();
                System.out.print("New Subject: ");
                teacher.subject = sc.nextLine();
                System.out.print("New Department: ");
                String newDept = sc.nextLine();

                if (!teacher.department.equals(newDept)) {
                    updateDepartmentTeacherCount(teacher.department, -1);
                    teacher.department = newDept;
                    updateDepartmentTeacherCount(newDept, 1);
                }

                return;
            }
        }
        System.out.println("Teacher not found.");
    }

    public static void searchTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.id.equals(id)) {
                System.out.println(teacher);
                return;
            }
        }
        System.out.println("Teacher not found.");
    }

    public static void deleteTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of teacher to delete: ");
        String id = sc.nextLine();
        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()) {
            Teacher teacher = iterator.next();
            if (teacher.id.equals(id)) {
                updateDepartmentTeacherCount(teacher.department, -1);
                iterator.remove();
                System.out.println("Teacher removed successfully.");
                return;
            }
        }
        System.out.println("Teacher not found.");
    }

    public static void displayAllTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
        } else {
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }

    public static void displayDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            for (Department dept : departments) {
                System.out.println(dept);
            }
        }
    }

    public static void displayStudentsByDepartment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Department Name: ");
        String department = sc.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.department.equals(department)) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found in this department.");
        }
    }

    public static void displayTeachersByDepartment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Department Name: ");
        String department = sc.nextLine();
        sc.nextLine();
        boolean found = false;
        for (Teacher teacher : teachers) {
            if (teacher.department.equals(department)) {
                System.out.println(teacher);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No teachers found in this department.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nUniversity Database Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Add Teacher");
            System.out.println("7. Update Teacher");
            System.out.println("8. Search Teacher");
            System.out.println("9. Delete Teacher");
            System.out.println("10. Display All Teachers");
            System.out.println("11. Display All Departments");
            System.out.println("12. Display Students by Department");
            System.out.println("13. Display Teachers by Department");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    addTeacher();
                    break;
                case 7:
                    updateTeacher();
                    break;
                case 8:
                    searchTeacher();
                    break;
                case 9:
                    deleteTeacher();
                    break;
                case 10:
                    displayAllTeachers();
                    break;
                case 11:
                    displayDepartments();
                    break;
                case 12:
                    displayStudentsByDepartment();
                    break;
                case 13:
                    displayTeachersByDepartment();
                    break;
                case 14:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}