package duke.logic;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TasksEnum;

public enum LCommandsEnum {
    DELETE() {
        @Override
        public String helpMessage() {
            return "Usage: delete <task number>\n" +
                    "For example: delete 1\n" +
                    "This will delete the first task in the list.";
        }

        /**
         * Runs the delete command.
         *
         * @param taskNumber The task number to be deleted. This is the number that the user sees.
         * @param taskList The task list that the user is using.
         * @return the deleted task.
         */
        public Task run(int taskNumber, TaskList taskList) {
            return taskList.removeTask(taskNumber);
        }
    }, HELP() {
        @Override
        public String helpMessage() {
            return "Usage: help [command]\n" +
                    "For example: help\n" +
                    "This will display the help messages for every command. Alternatively,\n" +
                    "             help list\n" +
                    "This will display the help message for the list command.";
        }


    }, LIST() {
        @Override
        public String helpMessage() {
            return "Usage: list\n" +
                    "This will display the list of tasks.";
        }
    }, BYE() {
        @Override
        public String helpMessage() {
            return "Usage: bye\n" +
                    "This will quit the Duke chatbot.";
        }
    }, DONE() {
        @Override
        public String helpMessage() {
            return "Usage: done <task number>\n" +
                    "For example: done 1\n" +
                    "This will mark the first task as done.";
        }

        /**
         * Runs the done command.
         *
         * @param taskNumber The task number to be marked as done. This is the number that the user sees.
         * @param taskList The task list that the user is using.
         * @return The task that is marked as done.
         */
        public Task run(int taskNumber, TaskList taskList) {
            if (!taskList.markAsDone(taskNumber)) { // task already marked as done
                throw new DukeException("You have already marked this task (%s) as done",
                        taskList.getTask(taskNumber));
            }
            return taskList.getTask(taskNumber);
        }
    }, UPCOMING() {
        @Override
        public String helpMessage() {
            return "Usage: upcoming\n" +
                    "This will display all the upcoming tasks, in chronological order.";
        }

        /**
         * Runs the upcoming command.
         *
         * @param taskList The task list that the user is using.
         * @param ui The user interface that the user is using.
         */
        public void run(TaskList taskList, Ui ui) {
            ui.printUpcomingTasks(taskList.getTasks());
        }
    }, TODO() {
        @Override
        public String helpMessage() {
            return "Usage: todo <task description>\n" +
                    "For example: todo Quit smoking\n" +
                    "This will add a todo task \"Quit smoking\" to the list.";
        }
    }, EVENT() {
        @Override
        public String helpMessage() {
            return "Usage: event <task description> /at <date and/or time>" +
                    "Where date and time is in the format: D/M/YYYY hh:mm\n" +
                    "For example: event Shopee 21.7 sale /at 21/7/2021\n" +
                    "This will add an event task \"Shopee 21.7 sale\" to the list," +
                    " with the date on 21 July 2021, 23:59 by default.\n" +
                    "             event first date /at 31/12/2090 12:00\n" +
                    "This will add an event task \"first date\" to the list," +
                    " with the date on 31 December 2090 on 12pm." ;
        }

    }, DEADLINE() {
        @Override
        public String helpMessage() {
            return "Usage: deadline <task description> /by <date and/or time>" +
                    "Where date and time is in the format: D/M/YYYY hh:mm\n" +
                    "For example: deadline haircut /by 19:00\n" +
                    "This will add a deadline task \"haircut\" to the list," +
                    " with today's date (or tomorrow if it is past 19:00) and the time 19:00.\n" +
                    "             deadline give $1 million to charity /by 31/12/2090 12:00\n" +
                    "This will add a deadline task \"give $1 million to charity\" to the list," +
                    " with the date on 31 December 2090 on 12pm." ;
        }
    };

    /**
     * Gets the help message of the specific command.
     * @return the associated string.
     */
    public abstract String helpMessage();



    /**
     * Attempts to add the task to the tasklist based on the user command.
     * @param tasksEnum the action of either todo, event or deadline
     * @param otherInput the rest of the string without the action
     * @param taskList the list of task to be added to
     * @return the task that is added.
     */
    private static Task addTask(TasksEnum tasksEnum, String otherInput, TaskList taskList) {
        boolean addTaskIsSuccessful;
        Task result = tasksEnum.getTask(otherInput);
        addTaskIsSuccessful = taskList.addTask(result);
        if (!addTaskIsSuccessful) {
            throw new DukeException("Unable to add task. List is full. Consider deleting some tasks");
        }
        return result;
    }
}