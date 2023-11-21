import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.exit;

/**
 * Creating the class UniversityCourseManagementSystem,
 * which provides the basis of the whole system where all processes are executed.
 */
public class UniversityCourseManagementSystem {
    /**
     * Initializing the list of courses.
     */
    private List<Course> courses;
    /**
     * Initializing the list of students.
     */
    private List<Student> students;
    /**
     * Initializing the list of professors.
     */
    private List<Professor> professors;
    /**
     * Initializing the list of wrongs to validate the data we enter that cannot be.
     */
    private List<String> wrongs;

    /**
     * Creating some new arrayLists in our lists.
     * Adding erroneous words for course titles and names.
     */
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

    /**
     * Adding information from the task condition tables.
     */
    public void fillInitialData() {
        /*
        About courses.
         */
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
        /*
        About students.
         */
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
        /*
        About professors.
         */
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

    /**
     * Initializing scanner, using java.util.Scanner for reading information from console.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Initializing system for system call.
     */
    private static UniversityCourseManagementSystem system = new UniversityCourseManagementSystem();
    /**
     * Main function, fillInitialData() call and running program by call run().
     * @param args it is args of command line.
     */
    public static void main(String[] args) {
        system.fillInitialData();
        system.run();
    }

    /**
     * Method to get student ID from class Student.
     * @param studentId from class Student for it.
     * @return if the conditions are met - student, in other case - nothing.
     */
    private Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.memberId == studentId) {
                return student;
            }
        }
        return null;
    }
    /**
     * Method to get course ID from class Course.
     * @param courseId from class Course for it.
     * @return if the conditions are met - course, in other case - nothing.
     */
    private Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    /**
     * Method to get professor ID from class Professor.
     * @param professorId from class Professor for it.
     * @return if the conditions are met - professor, in other case - nothing.
     */
    private Professor getProfessorById(int professorId) {
        for (Professor professor : professors) {
            if (professor.memberId == professorId) {
                return professor;
            }
        }
        return null;
    }
    /**
     * Method for outputting error information.
     */
    private void printWrongInputsAndExit() {
        out.println("Wrong inputs");
        exit(0);
    }
    /**
     * Method for reading incoming data and outputting the appropriate response
     * to the command by calling other methods.
     */
    public void run() {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            /*
            Checking that line is not empty.
             */
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
    /**
     * Method for responding to the "course" command.
     * Use it in the method run().
     */
    public void completionCourse() {
        String courseName = scanner.nextLine().toLowerCase();
        /*
        Checking that name of course consists of English letters and doesn't contain name of command as a name.
        */
        if (!courseName.matches("[a-z]+(_[a-z]+)*") || system.wrongs.contains(courseName)) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Checking that course is exists.
         */
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
        /*
        Program interruption if incoming data is not bachelor and not master.
         */
        if (!courseLevelStr.equalsIgnoreCase("bachelor")
                && !courseLevelStr.equalsIgnoreCase("master")) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If all checks are passed, the course is added.
         */
        CourseLevel courseLevel = CourseLevel.valueOf(courseLevelStr.toUpperCase());
        Course course = new Course(courseName, courseLevel);
        system.courses.add(course);
        out.println("Added successfully");
    }
    /**
     * Method for responding to the "student" command.
     * Use it in the method run().
     */
    public void completionStudent() {
        String studentName = scanner.nextLine().toLowerCase();
        /*
        Checking that name of student consists of English letters and doesn't contain name of command as a name.
        */
        if (!studentName.matches("[a-z]+") || system.wrongs.contains(studentName)) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If check is passed, the student is added.
         */
        Student student = new Student(studentName);
        system.students.add(student);
        out.println("Added successfully");
    }
    /**
     * Method for responding to the "professor" command.
     * Use it in the method run().
     */
    public void completionProfessor() {
        String professorName = scanner.nextLine().toLowerCase();
        /*
        Checking that name of professor consists of English letters and doesn't contain name of command as a name.
        */
        if (!professorName.matches("[a-z]+") || system.wrongs.contains(professorName)) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If check is passed, the professor is added.
         */
        Professor professor = new Professor(professorName);
        system.professors.add(professor);
        out.println("Added successfully");
    }
    /**
     * Method for responding to the "enroll" command.
     * Use it in the method run().
     */
    public void completionEnroll() {
        /*
        Checking that the studentId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing studentId.
         */
        int studentId = Integer.parseInt(scanner.nextLine());
        /*
        Checking that courseId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing courseId.
         */
        int courseId = Integer.parseInt(scanner.nextLine());
        Student student = system.getStudentById(studentId);
        Course course = system.getCourseById(courseId);
        /*
        Verification that such course and student exists.
         */
        if (student == null || course == null) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If all checks are passed, the student enrolled to the course.
         */
        if (student.enroll(course)) {
            out.println("Enrolled successfully");
        }
    }
    /**
     * Method for responding to the "drop" command.
     * Use it in the method run().
     */
    public void completionDrop() {
        /*
        Checking that the studentId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing studentId.
         */
        int studentId = Integer.parseInt(scanner.nextLine());
        /*
        Checking that courseId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing courseId.
         */
        int courseId = Integer.parseInt(scanner.nextLine());
        Student student = system.getStudentById(studentId);
        Course course = system.getCourseById(courseId);
        /*
        Verification that such course and student exists.
         */
        if (student == null || course == null) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If all checks are passed, the student dropped from the course if he enrolled in it.
         */
        if (student.drop(course)) {
            out.println("Dropped successfully");
        } else {
            out.println("Student is not enrolled in this course");
            exit(0);
        }
    }
    /**
     * Method for responding to the "exempt" command.
     * Use it in the method run().
     */
    public void completionExempt() {
        /*
        Checking that the professorId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing professorId.
         */
        int professorId = Integer.parseInt(scanner.nextLine());
        /*
        Checking that the courseId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing courseId.
         */
        int courseId = Integer.parseInt(scanner.nextLine());
        Professor professor = system.getProfessorById(professorId);
        Course course = system.getCourseById(courseId);
        /*
        Verification that such course and professor exists.
         */
        if (professor == null || course == null) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If all checks are passed, the professor exempted course if he does teaching in it.
         */
        if (professor.exempt(course)) {
            out.println("Professor is exempted");
        } else {
            out.println("Professor is not teaching this course");
            exit(0);
        }
    }
    /**
     * Method for responding to the "teach" command.
     * Use it in the method run().
     */
    public void completionTeach() {
        /*
        Checking that the professorId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing professorId.
         */
        int professorId = Integer.parseInt(scanner.nextLine());
        /*
        Checking that the courseId is a number.
         */
        if (!scanner.hasNextInt()) {
            printWrongInputsAndExit();
            return;
        }
        /*
        Initializing courseId.
         */
        int courseId = Integer.parseInt(scanner.nextLine());
        Professor professor = system.getProfessorById(professorId);
        Course course = system.getCourseById(courseId);
        /*
        Verification that such course and professor exists.
         */
        if (professor == null || course == null) {
            printWrongInputsAndExit();
            return;
        }
        /*
        If all checks are passed, the professor assigned to teach this course.
         */
        if (professor.teach(course)) {
            out.println("Professor is successfully assigned to teach this course");
        }
    }
}

/**
 * Interface for enrolling and dropping courses.
 */
interface Enrollable {
    boolean enroll(Course course);
    boolean drop(Course course);
}

/**
 * Enumeration for course levels bachelor and master.
 */
enum CourseLevel {
    BACHELOR,
    MASTER
}

/**
 * abstract class University Member.
 */
abstract class UniversityMember {
    /**
     * Initializing private static int numberOfMembers for counting numbers of members.
     * For summation assigning it zero.
     */
    private static int numberOfMembers = 0;
    /**
     * Initializing protected int memberId.
     */
    protected int memberId;
    /**
     * Initializing protected String memberName.
     */
    protected String memberName;

    /**
     * Method UniversityMember for counting numbers of members.
     * @param memberName use it for saving like member of university.
     */
    UniversityMember(String memberName) {
        this.memberId = ++numberOfMembers;
        this.memberName = memberName;
    }
}

/**
 * Class Student that extends UniversityMember and implements Enrollable.
 */
class Student extends UniversityMember implements Enrollable {
    /**
     * Initializing private static final int MAX_ENROLLMENT.
     */
    private static final int MAX_ENROLLMENT = 3;
    /**
     * Initializing private list of course List<Course> enrolledCourses.
     */
    private List<Course> enrolledCourses;

    /**
     * Method Student for adding.
     * @param memberName that presents student
     */
    Student(String memberName) {
        super(memberName);
        this.enrolledCourses = new ArrayList<>();
    }

    /**
     * From interface method enroll for enrolling and checking that we can enroll.
     * @param course for enrolling in course
     * @return true or false because it is boolean function
     */
    @Override
    public boolean enroll(Course course) {
        /*
        Checking whether the student is enrolled in the course or not.
         */
        if (enrolledCourses.contains(course)) {
            out.println("Student is already enrolled in this course");
            exit(0);
            return false;
        }
        /*
        Maximum enrollment test.
         */
        if (enrolledCourses.size() >= MAX_ENROLLMENT) {
            out.println("Maximum enrollment is reached for the student");
            exit(0);
            return false;
        }
        /*
        Checking for course completion.
         */
        if (course.isFull()) {
            out.println("Course is full");
            exit(0);
            return false;
        }
        /*
        Enrolled of courses.
         */
        enrolledCourses.add(course);
        course.addStudent(this);
        return true;
    }

    /**
     * From interface method drop for dropping and checking that we can drop.
     * @param course for dropping in course
     * @return true or false because it is boolean function
     */
    @Override
    public boolean drop(Course course) {
        /*
        If course isn't enrolled we can't drop it.
         */
        if (!enrolledCourses.contains(course)) {
            return false;
        }
        /*
        Removing.
         */
        enrolledCourses.remove(course);
        course.removeStudent(this);
        return true;
    }
}

/**
 * Class Professor that extends UniversityMember.
 */
class Professor extends UniversityMember {
    /**
     * Initializing private static final int MAX_LOAD.
     */
    private static final int MAX_LOAD = 2;
    /**
     * Initializing private List<Course> assignedCourses.
     */
    private List<Course> assignedCourses;

    /**
     * Method professor for adding.
     * @param memberName that presents professor
     */
    Professor(String memberName) {
        super(memberName);
        this.assignedCourses = new ArrayList<>();
    }

    /**
     * Boolean method teach for adding professor that teaching some course.
     * @param course for adding in it
     * @return true or false because it is boolean function
     */
    public boolean teach(Course course) {
        /*
        Max loading test.
         */
        if (assignedCourses.size() >= MAX_LOAD) {
            out.println("Professor's load is complete");
            exit(0);
            return false;
        }
        /*
        If professor is already teach this course we can't add him.
         */
        if (assignedCourses.contains(course)) {
            out.println("Professor is already teaching this course");
            exit(0);
            return false;
        }
        /*
        If all checks are passed, adding.
         */
        assignedCourses.add(course);
        return true;
    }

    /**
     * Boolean method exempt.
     * @param course for removing from it
     * @return true or false because it is boolean
     */
    public boolean exempt(Course course) {
        /*
        If professor isn't teaching this course we can't remove him.
         */
        if (!assignedCourses.contains(course)) {
            out.println("Professor is not teaching this course");
            exit(0);
            return false;
        }
        /*
        Removing.
         */
        assignedCourses.remove(course);
        return true;
    }
}

/**
 * Class Course.
 */
class Course {
    /**
     * Initializing private static int numberOfCourses.
     */
    private static int numberOfCourses = 0;
    /**
     * Initializing private static final int CAPACITY.
     */
    private static final int CAPACITY = 3;
    /**
     * Initializing private int courseId and getter for it.
     */
    private int courseId;
    public int getCourseId() {
        return courseId;
    }
    /**
     * Initializing private String courseName and getter for it.
     */
    private String courseName;
    public String getCourseName() {
        return courseName;
    }

    /**
     * Initializing private enum courseLevel and getter for it in this class.
     */
    private CourseLevel courseLevel;
    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    /**
     * Initializing private List<Student> enrolledStudents
     */
    private List<Student> enrolledStudents;

    /**
     * Method student for adding student.
     * @param courseName that presents name of student
     * @param courseLevel for presents level of student
     */
    Course(String courseName, CourseLevel courseLevel) {
        this.courseId = ++numberOfCourses;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.enrolledStudents = new ArrayList<>();
    }

    /**
     * Boolean isFull for checking that course have room for adding some course.
     * @return either true if there is course have some room or false if is not
     */
    public boolean isFull() {
        return enrolledStudents.size() >= CAPACITY;
    }

    /**
     * Boolean addStudent for adding student and checking that we can add him.
     * @param student return a value of student
     * @return true or false
     */
    public boolean addStudent(Student student) {
        /*
        Checking that we have room for a student and that we haven't enrolled it yet.
         */
        if (enrolledStudents.size() < CAPACITY && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    /**
     * Boolean for removing of student.
     * @param student return a value of student
     * @return removing
     */
    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }
}
