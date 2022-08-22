package Roster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao{

    public static final String AUDIT_FILE = "audit.txt";

    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }

        /*
        We are using a LocalDateTime object to create a timestamp for our audit log entries.
        Don't worry too much about LocalDateTime now – just use it as shown here.
        We will learn about the Java Date-Time API in a later lesson.
         */

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
