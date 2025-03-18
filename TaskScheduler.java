class Task {
    int taskID;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskID, String taskName, int priority, String dueDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = this;
    }
}

class TaskManagement {
    Task head;
    Task tail;
    Task currentTask;

    public void addTaskAtBeginning(int taskID, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            currentTask = head;
        } else {
            newTask.next = head;
            tail.next = newTask;
            head = newTask;
        }
    }

    public void addTaskAtEnd(int taskID, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            currentTask = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }

    public void addTaskAtSpecific(int taskID, String taskName, int priority, String dueDate, int pos) {
        if (pos == 1) {
            addTaskAtBeginning(taskID, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        Task curr = head;
        for (int i = 1; i < pos - 1 && curr.next != head; i++) {
            curr = curr.next;
        }
        newTask.next = curr.next;
        curr.next = newTask;
        if (curr == tail) {
            tail = newTask;
        }
    }

    public void removeTask(int taskID) {
        if (head == null) return;
        Task curr = head, prev = null;
        do {
            if (curr.taskID == taskID) {
                if (curr == head) {
                    head = head.next;
                    tail.next = head;
                } else if (curr == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    public void viewCurrentTask() {
        if (currentTask != null) {
            System.out.println("Current Task: " + currentTask.taskName + " (Priority: " + currentTask.priority + ")");
        }
    }

    public void moveToNextTask() {
        if (currentTask != null) {
            currentTask = currentTask.next;
            viewCurrentTask();
        }
    }

    public void displayAllTasks() {
        if (head == null) return;
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskID + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) return;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println("Task Found: " + temp.taskName + " (ID: " + temp.taskID + ")");
            }
            temp = temp.next;
        } while (temp != head);
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        TaskManagement tm = new TaskManagement();
        tm.addTaskAtEnd(1, "Task A", 3, "2025-03-20");
        tm.addTaskAtEnd(2, "Task B", 1, "2025-03-21");
        tm.addTaskAtEnd(3, "Task C", 2, "2025-03-22");
        tm.displayAllTasks();
        tm.viewCurrentTask();
        tm.moveToNextTask();
        tm.searchByPriority(2);
        tm.removeTask(2);
        tm.displayAllTasks();
    }
}
//Task ID: 1, Name: Task A, Priority: 3, Due Date: 2025-03-20
//Task ID: 2, Name: Task B, Priority: 1, Due Date: 2025-03-21
//Task ID: 3, Name: Task C, Priority: 2, Due Date: 2025-03-22
//Current Task: Task A (Priority: 3)
//Current Task: Task B (Priority: 1)
//Task Found: Task C (ID: 3)
//Task ID: 1, Name: Task A, Priority: 3, Due Date: 2025-03-20
//Task ID: 3, Name: Task C, Priority: 2, Due Date: 2025-03-22