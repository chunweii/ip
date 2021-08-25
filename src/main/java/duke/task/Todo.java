package duke.task;

import java.time.LocalDateTime;

/**
 * A simple task with no deadline
 */
public class Todo extends Task {
    /**
     * Creates a Todo object that does not have any dates.
     *
     * @param description The description of the task to be done
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * There is no datetime associated with Todo so null is returned.
     *
     * @return null.
     */
    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString(); // No preposition
    }
}
