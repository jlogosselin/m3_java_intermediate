package Roster.dao;

import Roster.dto.Student;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.*;

///////////////////////////////////

public class ClassRosterDaoFileImpl implements ClassRosterDao{

    private Map<String, Student> students = new HashMap<>();
    //new roster text file
    private final String ROSTER_FILE;
    //public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    ClassRosterDao testDao;

    public ClassRosterDaoFileImpl(){
        ROSTER_FILE = "roster.txt";
    }

    public ClassRosterDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile;
    }

    /*
    This way, before every test runs, we will have created a new
    blank testroster.txt file using the FileWriter, and then used
    that as our fileName when instantiating our testDao - both ensuring
    that we are starting with a fresh, empty Dao object, as well as
    minimizing our interference with the normal application's data
    stored in the roster.txt file.
     */

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        loadRoster();
        Student newStudent = this.students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        loadRoster();
        return new ArrayList(this.students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        return this.students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        loadRoster();
        Student removedStudent = this.students.remove(studentId);
        writeRoster();
        return removedStudent;
    }


    private Student unmarshallStudent(String studentAsText){

        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);

        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterPersistenceException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        Student currentStudent;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            this.students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close scanner
        scanner.close();
    }


    private String marshallStudent(Student aStudent){

        String studentAsText = aStudent.getStudentId() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();

        return studentAsText;
    }


    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *
     * @throws ClassRosterPersistenceException if an error occurs writing to the file
     */
    private void writeRoster() throws ClassRosterPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException(
                    "Could not save student data.", e);
        }

        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            studentAsText = marshallStudent(currentStudent);
            out.println(studentAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }


}

