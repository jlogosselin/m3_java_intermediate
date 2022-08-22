package Roster.controller;

import Roster.dao.ClassRosterDao;
import Roster.dao.ClassRosterDaoException;
import Roster.dto.Student;
import Roster.ui.ClassRosterView;

import java.util.List;

public class ClassRosterController {

    public ClassRosterController(){}

    private ClassRosterView view;
    private ClassRosterDao dao;

    public ClassRosterController(ClassRosterView v, ClassRosterDao d){
        this.view = v;
        this.dao = d;
    }

    /*
    Why are we passing an instance of the ClassRosterView into our constructor?
    ClassRosterView is not an interface and we won't have any other implementations of it,
    so why use dependency injection here?

    The reason is that we are going to change ClassRosterView so that its dependency
    is injected into its constructor.

    We don't want to make the ClassRosterController responsible for injecting the right
    UserIO implementation into ClassRosterView;
    that is the job of the ENTRYPOINT_CLASSROSTER class!
     */


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassRosterDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterDaoException {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() throws ClassRosterDaoException {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterDaoException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterDaoException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }


}

