class Movie{
    String movieTitle;
    String director;
    int yearOfRelease;
    int rating;
    Movie next;
    Movie prev;

    public Movie(String movieTitle, String director, int yearOfRelease, int rating) {
        this.movieTitle = movieTitle;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = this.prev =null;
    }
}

class MovieSystem{
    Movie head;

    public void addMovie(String movieTitle, String director, int yearOfRelease, int rating) {
        Movie newMovie = new Movie(movieTitle, director, yearOfRelease, rating);
        if (head == null) {
            head = newMovie;
        }
        else {
            Movie temp = head;

            while(temp.next != null) {
                temp = temp.next;
            }

            temp.next = newMovie;
            newMovie.prev = temp;
        }
    }

    public void removeMovie(String movieTitle) {
        if(head == null) {
            return;
        }

        Movie curr = head;
        Movie prevv = null;

        while(curr != null) {
            if(curr.movieTitle.equals(movieTitle)){
                if(curr == head) {
                    head = curr.next;
                    if(head != null) {
                        head.prev = null;
                    }
                }
                else {
                    curr.prev.next = curr.next;
                    if(curr.next != null) {
                        curr.next.prev = curr.prev;
                    }
                    return;
                }
            }
        }
    }

    public String searchMovieByDirector(String director){
        Movie curr = head;
        String movies = "";

        while(curr != null){
            if(curr.director.equals(director)){
                if(movies.length() > 0 ) movies += ",";
                movies += curr.movieTitle;
            }
            curr = curr.next;
        }

        if(movies.length()>0) {
            return movies;
        }

        return "No Movies Found";
    }

    public void displayMoviesInForward() {
        Movie curr = head;
        while(curr != null) {
            System.out.print("\nMovie Title : "+ curr.movieTitle + "\nDirector : " + curr.director + "\nYear of release :  " + curr.yearOfRelease + "\n Rating :  " + curr.rating + " ");
            curr = curr.next;
        }
    }

    public void displayMoviesInBackward() {
        Movie tail = head;

        while(tail.next != null) {
            tail = tail.next;
        }

        while(tail != null) {
            System.out.print("\nMovie Title : " + tail.movieTitle + "\nDirector : " + tail.director + "\nYear of release :  " + tail.yearOfRelease + "\nRating : " + tail.rating + " ");
            tail = tail.prev;
        }
    }

    public void updateRating(String movieTitle,int rating) {
        Movie curr = head;
        while(curr != null) {
            if(curr.movieTitle.equals(movieTitle)){
                curr.rating = rating;
                break;
            }
            curr = curr.next;
        }
    }
}
public class MovieManagement {
    public static void main(String[] args) {
        MovieSystem movieSystem = new MovieSystem();
        movieSystem.addMovie("Lagaan","Ashutosh Gowarikar",2001,4);
        movieSystem.addMovie("3 Idiots","Rajkumar Hirani",2009,5);

        movieSystem.displayMoviesInForward();
        System.out.println();
        movieSystem.displayMoviesInBackward();
        System.out.println();

        movieSystem.updateRating("Lagaan",5);
        movieSystem.displayMoviesInForward();
        System.out.println();

        System.out.println("\n" + movieSystem.searchMovieByDirector("Rajkumar Hirani"));

    }
}

//Movie Title : Lagaan
//Director : Ashutosh Gowarikar
//Year of release :  2001
//Rating :  4
//Movie Title : 3 Idiots
//Director : Rajkumar Hirani
//Year of release :  2009
//Rating :  5
//
//Movie Title : 3 Idiots
//Director : Rajkumar Hirani
//Year of release :  2009
//Rating : 5
//Movie Title : Lagaan
//Director : Ashutosh Gowarikar
//Year of release :  2001
//Rating : 4
//
//Movie Title : Lagaan
//Director : Ashutosh Gowarikar
//Year of release :  2001
//Rating :  5
//Movie Title : 3 Idiots
//Director : Rajkumar Hirani
//Year of release :  2009
//Rating :  5
//
//        3 Idiots
