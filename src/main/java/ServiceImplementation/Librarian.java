package ServiceImplementation;

import Enums.Position;
import Exceptions.LibraryException;
import Model.Book;
import Model.Library;
import Model.Person;
import Services.LibraryServices;
import Services.LendBook;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Librarian extends Person implements LibraryServices {
    public int count;
    private  static List<String> activeUsers = new ArrayList<>(); // List of Users with Book already

    private  static Queue<Person> personQueue = new LinkedList<>();

    public static Queue<Person> getPersonQueue() {return personQueue;}

    public static void setPersonQueue(Queue<Person> personQueue) {Librarian.personQueue = personQueue;}

    public static Queue<Person> getPriorityQueue() {return priorityQueue;}

    public static void setPriorityQueue(Queue<Person> priorityQueue) {Librarian.priorityQueue = priorityQueue;}

    private  static  Queue<Person> priorityQueue = new PriorityQueue<>((one, two) -> Integer.compare(two.getPriority(), one.getPriority()));

    public Librarian(String name, Position position){
        super(name, position);
    }

    public int getCount() {
        return count;
    }

    @Override
    public void registerUser(Person person) {
        registerUser2.accept(person);
    }

//    Predicate<Person>che

    private final Consumer<Person> registerUser2 = person -> {
        if(person != null){
            if(!personQueue.contains(person)){personQueue.add(person);}
            if(!priorityQueue.contains(person)){priorityQueue.add(person);}
            count++;
        }
    };

    Function<Book, String> addBookToLibrary = book -> {
        if(Library.getAvailableBooks().containsKey(book.getBookTitle())) {
            int old = Library.getAvailableBooks().get(book.getBookTitle());
            Library.getAvailableBooks().put(book.getBookTitle(),old + book.getNumberOfBooks());
            return book.getNumberOfBooks()+" new " +book.getBookTitle()+" added.";
        }else{
            Library.getAvailableBooks().put(book.getBookTitle(), book.getNumberOfBooks());
            return book.getNumberOfBooks()+" copy of "+book.getBookTitle()+ " added successfully";
        }
    };

    @Override
    public String addBookToLibrary(Book book){
        return addBookToLibrary.apply(book);
    }

    @Override
    public String lendBookToUserByPriority(Book book) throws LibraryException {
        if(!priorityQueue.isEmpty() ) {
            final Person person = priorityQueue.remove();
            return process(person,book);
        } else return "please register with the Library first!!!";

    }
    @Override
    public String lendBookToUserByFifo( Book book) throws LibraryException {
        if(!personQueue.isEmpty() ) {
            final Person person = personQueue.remove();
            return process(person,book);
        } else return "please register with the Library first!!!";

    }

////    The return type of ths method is the name of the functional interface
//    public LendBook lendBookPriority = (book) -> {
//        if(!priorityQueue.isEmpty() ) {
//            final Person person = priorityQueue.remove();
//            return process(person,book);
//        } else return "please register with the Library first!!!";
//    };

    public String process(Person person,  Book book){
        //check if book is in the library, check if the book has not been borrowed,
        return Library.getAvailableBooks().containsKey(book.getBookTitle()) ?
                updateBookRecords(person, book) : "Sorry "
                + person.getFirstName() + " the book, " + book.getBookTitle() + " is not in our Library yet";
    }

    private String updateBookRecords(Person person, Book book){
        Integer copiesAvailable = Library.getAvailableBooks().get(book.getBookTitle());//give the book to the user and update his set of books
        if(copiesAvailable != 0){
            Set<String> borrowedBooks = new HashSet<>();
            borrowedBooks.add(book.getBookTitle());
            person.getBooksBorrowed().add(book.getBookTitle());
            Library.getAvailableBooks().put(book.getBookTitle(),copiesAvailable-1);
            return  "1 copy of the book "+book.getBookTitle()+" is borrowed to "+person.getFirstName();
        } else throw new LibraryException("No copy of the book "+book.getBookTitle()+" in the library");

    }

    @Override
    public String collectBookFromUser(Person person, String bookTitle) {
        Set<String> borrowedBooks = person.getBooksBorrowed();
        if(borrowedBooks.contains(bookTitle)){
            Library.getAvailableBooks().put(bookTitle,1);
            borrowedBooks.remove(bookTitle);
            return "The book "+bookTitle+ " has been returned to the library by "+person.getFirstName();
        }else
            return "This book has not been borrowed by "+person.getFirstName();
    }
}
