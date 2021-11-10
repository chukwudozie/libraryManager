package Model;

import ServiceImplementation.Librarian;

import java.util.*;

public class Library {
    private Book book;
    private Librarian librarian;
    private static Map<String, Integer> availableBooks = new HashMap<>();
    private static Map<String, Integer>lendedBooks = new HashMap<>();
    private static List<String> currentUsers = new ArrayList<>();
//    private PriorityQueue<Person> users = new PriorityQueue<>(5,new UserComparator());

    public static Map<String, Integer> getAvailableBooks() {
        return availableBooks;
    }

    public static void setAvailableBooks(Map<String, Integer> availableBooks) { Library.availableBooks = availableBooks;}

    public Library(Librarian librarian) {this.librarian = librarian;}
}
