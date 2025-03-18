class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = this;
    }
}

class TicketReservationSystem {
    Ticket head, tail;
    int totalTickets = 0;

    public void addTicket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        totalTickets++;
    }

    public void removeTicket(int ticketID) {
        if (head == null) return;
        Ticket temp = head, prev = null;
        do {
            if (temp.ticketID == ticketID) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = temp.next;
                }
                totalTickets--;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        System.out.println("Booked Tickets:");
        do {
            System.out.println("Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String key) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(key) || temp.movieName.equalsIgnoreCase(key)) {
                System.out.println("Found: Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No matching tickets found.");
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}

public class TicketReservationApp {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(101, "Alice", "Inception", 12, "18:00");
        system.addTicket(102, "Bob", "Interstellar", 15, "20:00");
        system.addTicket(103, "Charlie", "Inception", 22, "18:00");
        system.displayTickets();
        system.searchTicket("Inception");
        system.removeTicket(102);
        system.displayTickets();
        System.out.println("Total Booked Tickets: " + system.getTotalTickets());
    }
}
//Booked Tickets:
//Ticket ID: 101, Customer: Alice, Movie: Inception, Seat: 12, Time: 18:00
//Ticket ID: 102, Customer: Bob, Movie: Interstellar, Seat: 15, Time: 20:00
//Ticket ID: 103, Customer: Charlie, Movie: Inception, Seat: 22, Time: 18:00
//Found: Ticket ID: 101, Customer: Alice, Movie: Inception, Seat: 12, Time: 18:00
//Found: Ticket ID: 103, Customer: Charlie, Movie: Inception, Seat: 22, Time: 18:00
//Booked Tickets:
//Ticket ID: 101, Customer: Alice, Movie: Inception, Seat: 12, Time: 18:00
//Ticket ID: 103, Customer: Charlie, Movie: Inception, Seat: 22, Time: 18:00
//Total Booked Tickets: 2
