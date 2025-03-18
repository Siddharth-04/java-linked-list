class Book{
    String title;
    String author;
    String genre;
    int bookId;
    boolean available;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.available = available;
        this.next = null;
        this.prev = null;
    }
}

class BookManagement{
    Book head;

    public void addBookAtBeginning(String title, String author, String genre, int bookId,boolean available) {
        Book book = new Book(title, author, genre, bookId, available);

        if(head == null){
            head = book;
        }
        else{
            book.next = head;
            head.prev = book;
            head = book;
        }

    }

    public void addBookAtEnd(String title, String author, String genre, int bookId,boolean available) {
        Book book = new Book(title, author, genre, bookId, available);
        if(head == null){
            head = book;
        }
        else{
            Book current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = book;
            book.prev = current;
        }
    }

    public void addBookAtSpecific(int pos,String title, String author, String genre, int bookId,boolean available) {
        Book book = new Book(title, author, genre, bookId, available);
        Book current = head;
        Book prevv = head;

        if(head == null){
            head = book;
        }
        else{
            for(int i=1 ; i<pos ; i++){
                prevv = current;
                current = current.next;
            }

            prevv.next = book;
            book.prev = prevv;

            book.next = current;
            current.prev = book;
        }
    }

    public void removeBook(int bookId){
        Book current = head;
        Book prevv = current;

        while(current != null){
            if(current.bookId == bookId){
                prevv.next = current.next;
                current.next.prev = prevv;
                break;
            }
            else{
                prevv = current;
                current = current.next;
            }
        }
    }

    public void searchBook(String title){
        Book current = head;
        while(current != null){
            if(current.title.equals(title)){
                System.out.println("\nBook details :"
                + "\nTitle: " + current.title
                + "\nAuthor: " + current.author
                + "\nGenre: " + current.genre
                + "\nBook ID: " + current.bookId
                + "\nAvailable: " + current.available);
                break;
            }
            else{
                current = current.next;
            }
        }
    }

    public void updateAvailibilty(int bookId){
        Book current = head;
        while(current != null){
            if(current.bookId == bookId){
                current.available = !current.available;
                break;
            }
            else{
                current = current.next;
            }
        }
    }

    public void displayBooksInForward() {
        Book curr = head;
        while(curr != null) {
            System.out.print("\nBook Title : "+ curr.title +
                    "\nAuthor : " + curr.author +
                    "\nGenre :  " + curr.genre +
                    "\nID :  " + curr.bookId + "\n");
            curr = curr.next;
        }
    }

    public void displayBooksInBackward() {
        Book tail = head;

        while(tail.next != null) {
            tail = tail.next;
        }

        while(tail != null) {
            System.out.print("\nBook Title : "+ tail.title +
                    "\nAuthor : " + tail.author +
                    "\nGenre :  " + tail.genre +
                    "\nID :  " + tail.bookId + "\n");
            tail = tail.prev;
        }
    }

    public void countBooks() {
        Book current = head;
        int count = 0;

        while(current != null) {
            count++;
            current = current.next;
        }

        System.out.println("Total Books : " + count);
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookManagement bookManagement = new BookManagement();
        bookManagement.addBookAtBeginning("The Batman", "Bob Kane", "SuperHero", 101,true);
        bookManagement.addBookAtEnd("The Spiderman", "Bob Kane", "SuperHero", 102,true);
        bookManagement.addBookAtEnd("The Witchman", "Bob Kane", "SuperHero", 103,true);

        bookManagement.displayBooksInForward();
        bookManagement.displayBooksInBackward();

        bookManagement.searchBook("The Batman");

        bookManagement.countBooks();

        bookManagement.updateAvailibilty(101);

        bookManagement.removeBook(102);

        bookManagement.displayBooksInForward();
    }
}

//Book Title : The Batman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  101
//
//Book Title : The Spiderman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  102
//
//Book Title : The Witchman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  103
//
//Book Title : The Witchman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  103
//
//Book Title : The Spiderman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  102
//
//Book Title : The Batman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  101
//
//Book details :
//Title: The Batman
//Author: Bob Kane
//Genre: SuperHero
//Book ID: 101
//Available: true
//Total Books : 3
//
//Book Title : The Batman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  101
//
//Book Title : The Witchman
//Author : Bob Kane
//Genre :  SuperHero
//ID :  103
