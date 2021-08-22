package duke;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * duke.Task is a general class for any task that is to be saved by duke.Duke.
 */
public abstract class Task {
    private final String description, taskType;
    private boolean isDone;

    /**
     * The constructor for a task object
     * @param description The description of the task
     * @param taskType The type of task (to<></>do/event/deadline)
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    /**
     * Gets the status string <code>[&lt;taskType&gt;][&lt;isDone&gt;]</code>
     *
     * @return the associated string
     */
    public String getStatus() {
        return (isDone ? String.format("[%s][X]", taskType) : String.format("[%s][ ]", taskType)); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return taskType;
    }

    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Gets the date and time of the task, or null if not applicable.
     *
     * @return the associated LocalDateTime object
     */
    public abstract LocalDateTime getDateTime();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatus(), this.getDescription());
    }
}