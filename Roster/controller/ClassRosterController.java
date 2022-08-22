package Roster.controller;

import Roster.dao.ClassRosterDao;
import Roster.dao.ClassRosterPersistenceException;
import Roster.dto.Student;
import Roster.service.ClassRosterDataValidationException;
import Roster.service.ClassRosterDuplicateIdException;
import Roster.service.ClassRosterServiceLayer;
import Roster.ui.ClassRosterView;

import java.util.List;

public class ClassRosterController {

    public ClassRosterController(){}

    private ClassRosterView view;
    //private ClassRosterDao dao;
    private ClassRosterServiceLayer service;

    /*
    public ClassRosterController(ClassRosterView v, ClassRosterDao d){
        this.view = v;
        this.dao = d;
    }

    */
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
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
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

                                    // keep an eye on this!

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        Student student = service.getStudent(studentId);
        view.displayRemoveResult(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////


    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }


}

