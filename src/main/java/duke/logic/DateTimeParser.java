package duke.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.DukeException;


/**
 * Enables simpler parsing of date and time for events and deadlines.
 */
public class DateTimeParser {
    private final LocalTime time;
    private final LocalDate date;

    /**
     * Creates a new instance of a Date
     *
     * @param dateTime The string to be parsed
     */
    public DateTimeParser(String dateTime) {
        String[] dateAndTime = Arrays.stream(dateTime.split("[ |,]", 2))
            .map(String::trim).toArray(String[]::new);
        try {
            if (dateAndTime.length == 1) {
                if (dateAndTime[0].contains("/")) { // User entered date
                    date = LocalDate.parse(dateAndTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    time = LocalTime.parse("23:59");
                } else { // User likely entered time
                    time = LocalTime.parse(dateAndTime[0], DateTimeFormatter.ofPattern("H:m"));
                    date = time.isAfter(LocalTime.now()) ? LocalDate.now() : LocalDate.now().plusDays(1);
                }
            } else {
                date = LocalDate.parse(dateAndTime[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
                time = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("H:m"));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time format. Please enter them in the format: d/M/YYYY H:m.\n"
                + "For example: 23/8/2021 14:00");
        }
    }

    /**
     * Returns the LocalDateTime object associated with the date and time represented in the data string.
     *
     * @param data the string containing the date and time of the task. Format is "yyyy-MM-dd HH:mm".
     * @return the LocalDateTime object
     */
    public static LocalDateTime getDateTimeFromDataString(String data) {
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Gets the date of this datetime.
     *
     * @return the local date object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of this datetime.
     *
     * @return the local time object.
     */
    public LocalTime getTime() {
        return time;
    }
}