package Roster;

import Roster.controller.ClassRosterController;
import Roster.dao.ClassRosterDao;
import Roster.dao.ClassRosterDaoFileImpl;
import Roster.ui.ClassRosterView;
import Roster.ui.UserIO;
import Roster.ui.UserIOConsoleImpl;

public class EntryPoint_Roster {
    public static void main(String[] args) {

        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myView, myDao);
        controller.run();
    }
}
