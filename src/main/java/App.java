import Enums.Position;
import Exceptions.LibraryException;
import Model.Book;
import Model.Library;
import ServiceImplementation.Librarian;
import ServiceImplementation.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
//        IMPLEMENTATION 1: Create Users to Make use of the Library
        Librarian librarian = new Librarian("Thomas", Position.LIBRARIAN);
        List<User> userList = List.of(
                new User("Chidi", Position.SENIOR_STUDENT),
                new User("Emeka", Position.JUNIOR_STUDENT),
                new User("Sir Mark",Position.TEACHER),
                new User("Christian", Position.JUNIOR_STUDENT),
                new User("Ebuka",Position.SENIOR_STUDENT),
                new User("Sir Josh",Position.TEACHER)
        );

//          Create Books
        Book book1 = new Book("Biolgy","Emeka",5);
        Book book2 = new Book("Maths","Stroud",6);


//        Populate the School Library with books to be borrowed
        System.out.println(librarian.addBookToLibrary(book1));
        System.out.println(librarian.addBookToLibrary(book2));

        System.out.println("Initial Books: " + Library.getAvailableBooks());


        userList.stream().forEach(librarian::registerUser);

        System.out.println(Librarian.getPriorityQueue().toString());


        System.out.println("\n\n");

        userList.stream().forEach(person -> {
            try {System.out.println(librarian.lendBookToUserByPriority(book1));
            }catch (LibraryException e){System.out.println(e);}
        });

//        userList.stream().forEach(person -> {
//            try {System.out.println(librarian.lendBookToUserByFifo(book2));
//            }catch (LibraryException e){System.out.println(e);}
//        });

        System.out.println("\n\n");

        userList.stream().forEach(librarian::registerUser);
        System.out.println(Librarian.getPriorityQueue().toString());

        System.out.println("\nRemaining Books: " + Library.getAvailableBooks());


    }
}
