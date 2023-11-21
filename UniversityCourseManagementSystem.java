import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.exit;

public class UniversityCourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;
    private List<Professor> professors;
    private List<String> wrongs;
    UniversityCourseManagementSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        professors = new ArrayList<>();
        wrongs = new ArrayList<>();
        wrongs.add("course");
        wrongs.add("student");
        wrongs.add("professor");
        wrongs.add("enroll");
        wrongs.add("drop");
        wrongs.add("exempt");
        wrongs.add("teach");
    }

    public void fillInitialData() {
        Course javaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        courses.add(javaBeginner);
        Course javaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        courses.add(javaIntermediate);
        Course pythonBasics = new Course("python_basics", CourseLevel.BACHELOR);
        courses.add(pythonBasics);
        Course algorithms = new Course("algorithms", CourseLevel.MASTER);
        courses.add(algorithms);
        Course advancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        courses.add(advancedProgramming);
        Course mathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        courses.add(mathematicalAnalysis);
        Course computerVision = new Course("computer_vision", CourseLevel.MASTER);
        courses.add(computerVision);

        Student student1 = new Student("Alice");
        students.add(student1);
        student1.enroll(javaBeginner);
        student1.enroll(javaIntermediate);
        student1.enroll(pythonBasics);
        Student student2 = new Student("Bob");
        students.add(student2);
        student2.enroll(javaBeginner);
        student2.enroll(algorithms);
        Student student3 = new Student("Alex");
        students.add(student3);
        student3.enroll(advancedProgramming);

        Professor professor1  = new Professor("Ali");
        professors.add(professor1);
        professor1.teach(javaBeginner);
        professor1.teach(javaIntermediate);
        Professor professor2  = new Professor("Ahmed");
        professors.add(professor2);
        professor2.teach(pythonBasics);
        professor2.teach(advancedProgramming);
        Professor professor3  = new Professor("Andrey");
        professors.add(professor3);
        professor3.teach(mathematicalAnalysis);
    }
    private void printWrongInputsAndExit() {
        out.println("Wrong inputs");
        exit(0);
    }
    public static Scanner scanner = new Scanner(System.in);
    public static UniversityCourseManagementSystem system = new UniversityCourseManagementSystem();
    public static void main(String[] args) {
        system.fillInitialData();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (input.equals("course")) {
                system.completionCourse();
            }  else if (input.equals("student")) {
                system.completionStudent();
            } else if (input.equals("professor")) {
                system.completionProfessor();
            } else if (input.equals("enroll")) {
                system.completionEnroll();
            } else if (input.equals("drop")) {
                system.completionDrop();
            } else if (input.equals("exempt")) {
                system.completionExempt();
            } else if (input.equals("teach")) {
                system.completionTeach();
            } else {
                printWrongInputsAndExit();
                return;
            }
        }
        scanner.close();
    }

    private Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.memberId == studentId) {
                return student;
            }
        }
        return null;
    }

    private Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    private Professor getProfessorById(int professorId) {
        for (Professor professor : professors) {
            if (professor.memberId == professorId) {
                return professor;
            }
        }
        return null;
    }
    public void completionCourse() {
        String courseName = scanner.nextLine().toLowerCase();
        if (!courseName.matches("[a-z]+(_[a-z]+)*") || system.wrongs.contains(courseName)) {
            printWrongInputsAndExit();
            return;
        }
        boolean isExists = false;
        for (Course existingCourse : system.courses) {
            if (existingCourse.getCourseName().equals(courseName)) {
                isExists = true;
                break;
            }
        }
        if (isExists) {
            out.println("Course exists");
            exit(0);
        }
        String courseLevelStr = scanner.nextLine();
        if (!courseLevelStr.equalsIgnoreCase("bachelor")
                && !courseLevelStr.equalsIgnoreCase("master")) {
            printWrongInputsAndExit();
            return;
        }
        CourseLevel courseLevel = CourseLevel.valueOf(courseLevelStr.toUpperCase());
        Course course = new Course(courseName, courseLevel);
        system.courses.add(course);
        out.println("Added successfully");
    }
    public void completionStudent() {
        String studentName = scanner.nextLine().toLowerCase();
        if (!studentName.matches("[a-z]+") || system.wrongs.contains(studentName)) {
            printWrongInputsAndExit();
            return;
        }
        Student student = new Student(studentName);
        system.students.add(student);
        out.println("Added successfully");
    }
    public void completionProfessor() {
        String professorName = scanner.nextLine().toLowerCase();
        if (!professorName.matches("[a-z]+") || system.wrongs.contains(professorName)) {
            printWrongInputsAndExit();
            return;
        }
        Professor professor = new Professor(professorName);
        system.professors.add(professor);
        out.println("Added successfully");
    }
    public void completionEnroll() {
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int studentId = Integer.parseInt(scanner.nextLine());
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int courseId = Integer.parseInt(scanner.nextLine());
        Student student = system.getStudentById(studentId);
        Course course = system.getCourseById(courseId);

        if (student == null || course == null) {
            printWrongInputsAndExit();
            return;
        }

        if (student.enroll(course)) {
            out.println("Enrolled successfully");
        }
    }
    public void completionDrop() {
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int studentId = Integer.parseInt(scanner.nextLine());
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        int courseId = Integer.parseInt(scanner.nextLine());
        Student student = system.getStudentById(studentId);
        Course course = system.getCourseById(courseId);

        if (student == null || course == null) {
            printWrongInputsAndExit();
            return;
        }

        if (student.drop(course)) {
            out.println("Dropped successfully");
        } else {
            out.println("Student is not enrolled in this course");
            exit(0);
        }
    }
    public void completionExempt() {
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int professorId = Integer.parseInt(scanner.nextLine());
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int courseId = Integer.parseInt(scanner.nextLine());
        Professor professor = system.getProfessorById(professorId);
        Course course = system.getCourseById(courseId);

        if (professor == null || course == null) {
            printWrongInputsAndExit();
            return;
        }

        if (professor.exempt(course)) {
            out.println("Professor is exempted");
        } else {
            out.println("Professor is not teaching this course");
            exit(0);
        }
    }
    public void completionTeach() {
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int professorId = Integer.parseInt(scanner.nextLine());
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }

        int courseId = Integer.parseInt(scanner.nextLine());
        Professor professor = system.getProfessorById(professorId);
        Course course = system.getCourseById(courseId);

        if (professor == null || course == null) {
            printWrongInputsAndExit();
            return;
        }

        if (professor.teach(course)) {
            out.println("Professor is successfully assigned to teach this course");
        }
    }
}

interface Enrollable {
    boolean enroll(Course course);
    boolean drop(Course course);
}

enum CourseLevel {
    BACHELOR,
    MASTER
}

abstract class UniversityMember {
    private static int numberOfMembers = 0;
    protected int memberId;
    protected String memberName;
    UniversityMember(String memberName) {
        this.memberId = ++numberOfMembers;
        this.memberName = memberName;
    }
}

class Student extends UniversityMember implements Enrollable {
    private static final int MAX_ENROLLMENT = 3;
    private List<Course> enrolledCourses;
    Student(String memberName) {
        super(memberName);
        this.enrolledCourses = new ArrayList<>();
    }
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    @Override
    public boolean enroll(Course course) {
        if (enrolledCourses.contains(course)) {
            out.println("Student is already enrolled in this course");
            exit(0);
            return false;
        }
        if (enrolledCourses.size() >= MAX_ENROLLMENT) {
            out.println("Maximum enrollment is reached for the student");
            exit(0);
            return false;
        }
        if (course.isFull()) {
            out.println("Course is full");
            exit(0);
            return false;
        }
        enrolledCourses.add(course);
        course.addStudent(this);
        return true;
    }
    @Override
    public boolean drop(Course course) {
        if (!enrolledCourses.contains(course)) {
            return false;
        }
        enrolledCourses.remove(course);
        course.removeStudent(this);
        return true;
    }
}

class Professor extends UniversityMember {
    private static final int MAX_LOAD = 2;
    private List<Course> assignedCourses;
    Professor(String memberName) {
        super(memberName);
        this.assignedCourses = new ArrayList<>();
    }
    public boolean teach(Course course) {
        if (assignedCourses.size() >= MAX_LOAD) {
            out.println("Professor's load is complete");
            exit(0);
            return false;
        }
        if (assignedCourses.contains(course)) {
            out.println("Professor is already teaching this course");
            exit(0);
            return false;
        }
        assignedCourses.add(course);
        return true;
    }
    public boolean exempt(Course course) {
        if (!assignedCourses.contains(course)) {
            out.println("Professor is not teaching this course");
            exit(0);
            return false;
        }
        assignedCourses.remove(course);
        return true;
    }
}

class Course {
    private static int numberOfCourses = 0;
    private static final int CAPACITY = 3;
    private int courseId;
    public int getCourseId() {
        return courseId;
    }
    private String courseName;
    public String getCourseName() {
        return courseName;
    }
    private CourseLevel courseLevel;
    public CourseLevel getCourseLevel() {
        return courseLevel;
    }
    private List<Student> enrolledStudents;
    Course(String courseName, CourseLevel courseLevel) {
        this.courseId = ++numberOfCourses;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.enrolledStudents = new ArrayList<>();
    }
    public boolean isFull() {
        return enrolledStudents.size() >= CAPACITY;
    }
    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < CAPACITY && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }
    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }
}
