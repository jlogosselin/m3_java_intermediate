package Roster;

import Roster.controller.ClassRosterController;
import Roster.dao.ClassRosterAuditDao;
import Roster.dao.ClassRosterAuditDaoFileImpl;
import Roster.dao.ClassRosterDao;
import Roster.dao.ClassRosterDaoFileImpl;
import Roster.service.ClassRosterServiceLayer;
import Roster.service.ClassRosterServiceLayerImpl;
import Roster.ui.ClassRosterView;
import Roster.ui.UserIO;
import Roster.ui.UserIOConsoleImpl;

public class EntryPoint_Roster {

    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        ClassRosterView myView = new ClassRosterView(myIo);
        // Instantiate the DAO
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // Instantiate the Audit DAO
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        ClassRosterController controller = new ClassRosterController(myService, myView);
        // Kick off the Controller
        controller.run();
    }
}
