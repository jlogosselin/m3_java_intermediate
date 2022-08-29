package DateTimeExamples;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateTimeExample {

    public static void main(String[] args) {

        //code below creates a new LocalDate object containing today's date:
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        //This example creates a new LocalDate object containing the date represented
        // by a well-formed ISO-8601 string:
        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);

        //The next example creates a new LocalDate object containing the date represented
        // by a well-formed date string of the pattern MM/dd/yyyy. Notice that this version
        // of the parse method requires both the date string and a parameter specifying
        // the pattern of the incoming date.
        // To do this, we use the static ofPattern method on the DateTimeFormatter:

        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);

        //LocalDate provides an easy way to calculate dates in the past or in the future,
        // based on an existing date using the plusYear, plusMonth, plusDay, minusYear, minusMonth,
        // and minusDay methods.
        // To use these methods, simply pass in the number of years, months, or days that
        // you want to add or subtract from the existing date and the method will return
        // a new LocalDate object. It is exactly as easy as it sounds:

        LocalDate past = ld.minusDays(8);
        System.out.println(past);

        //LocalDate also provides an easy way to determine the amount of time between
        // two dates via the until method. The until method compares two dates and returns
        // a Period object representing the difference in time between the two dates.
        // The Period class has getter methods for the years, months, and days values that
        // make up the time difference between the two dates. Continuing with our ld and past
        // variables, the code looks like this:

        Period diff = ld.until(past);
        System.out.println(diff);


        //Although ISO-8601 is the default date format for the Java Date-Time API,
        // dates can be displayed in a variety of formats. To do this, we use a combination
        // of the LocalDate.format() method and the DateTimeFormatter class.
        //The first example formats the LocalDate into a string with the date pattern MM/dd/yyyy:

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate ld2 = LocalDate.parse("02/07/2010", formatter);
        String formatted = ld2.format(formatter);
        System.out.println(formatted);

        //The next example also formats a LocalDate into a string.
        // This example illustrates the ability to format the date using special
        // characters and in whatever pattern is required for your project:

        formatted = ld.format(DateTimeFormatter.ofPattern("MM=dd=yyyy+=+=+="));
        System.out.println(formatted);

        //Finally, we look at using the DateTimeFormatter.ofLocalizedDate method.
        // This uses localization information from the system to determine how the
        // date should be formatted.

        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);


        //The conversion of a legacy Date object into a LocalDate object involves two steps.
        // First, you must convert the Date into a ZonedDateTime object and from there into a LocalDate.
        //The first step (converting the Date to the ZonedDateTime) consists of several pieces:
        //We convert the Date into an Instant. Essentially, we are converting the Date from human
        // time into machine time.
        //We convert the Instant derived from the legacy Date into a ZonedDateTime object using
        // the static ofInstant method. Here, we are essentially converting the machine time
        // Instant back into a human time ZonedDateTime object.
        // Notice that we must also pass a time zone id into the ofInstant method – we use the
        // system default of the machine the code is running on.
        //The second step is more straightforward – we simply call the toLocalDate method of our
        // ZonedDateTime object.

        Date legacyDate = new Date();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(
                legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);

    }
}
