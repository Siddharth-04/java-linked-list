class Process {
    int processID;
    int burstTime;
    int priority;
    Process next;

    public Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = this;
    }
}

class RoundRobinScheduler {
    Process head;
    Process tail;
    Process current;
    int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processID, int burstTime, int priority) {
        Process newProcess = new Process(processID, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            current = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    public void removeProcess(int processID) {
        if (head == null) return;
        Process temp = head, prev = null;
        do {
            if (temp.processID == processID) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = temp.next;
                }
                if (temp == current) current = current.next;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void executeProcesses() {
        if (head == null) return;
        int time = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0, processCount = 0;
        while (head != null) {
            System.out.println("Executing Process " + current.processID + " for " + Math.min(current.burstTime, timeQuantum) + " units.");
            time += Math.min(current.burstTime, timeQuantum);
            current.burstTime -= timeQuantum;
            if (current.burstTime <= 0) {
                totalTurnaroundTime += time;
                totalWaitingTime += time - current.burstTime - timeQuantum;
                int pid = current.processID;
                current = current.next;
                removeProcess(pid);
                processCount++;
            } else {
                current = current.next;
            }
            displayProcesses();
        }
        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processCount);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes left.");
            return;
        }
        Process temp = head;
        System.out.println("Current Processes in Queue:");
        do {
            System.out.println("Process ID: " + temp.processID + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

public class RoundRobinSchedulerApp {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.displayProcesses();
        scheduler.executeProcesses();
    }
}
//Current Processes in Queue:
//Process ID: 1, Burst Time: -9278182, Priority: 2
//Executing Process 1 for -9278182 units.
//Current Processes in Queue:
//Process ID: 1, Burst Time: -9278186, Priority: 2
//Executing Process 1 for -9278186 units.
//Current Processes in Queue:
//Process ID: 1, Burst Time: -9278190, Priority: 2
//Executing Process 1 for -9278190 units.
//Current Processes in Queue:
//Process ID: 1, Burst Time: -9278194, Priority: 2