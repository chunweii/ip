package duke;

import duke.logic.LStorage;
import duke.task.TaskList;
import duke.ui.TextCliUi;

/**
 * Duke is a personal assistant that allows users to keep track of events, deadlines and things to do.
 * The main method will start the personal assistant in the console.
 */
public class Duke {
    private final TaskList taskList;
    private final LStorage lStorage;

    /**
     * Creates a new instance of a duke chat-bot.
     *
     * @param filePath  the path where the data of the tasks will be saved
     * @param listLimit the maximum size of the task list
     */
    public Duke(String filePath, int listLimit) {
        taskList = new TaskList(listLimit);
        lStorage = new LStorage(filePath, taskList);
    }

    /**
     * Creates a new instance of a duke chat-bot, with default filepath and limit.
     */
    public Duke() {
        taskList = new TaskList(100);
        lStorage = new LStorage("./dukedata.txt", taskList);
    }

    /**
     * Runs the duke chat-bot with default filepath and limit.
     *
     * @param args irrelevant for now
     */
    public static void main(String[] args) {
        new Duke("./dukedata.txt", 100).run();
    }

    /**
     * Runs the duke chat-bot.
     */
    public void run() {
        TextCliUi ui = new TextCliUi();
        while (!ui.willExit()) {
            ui.checkInput(taskList, lStorage);
        }
    }
}
