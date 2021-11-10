package ServiceImplementation;

import Enums.Position;
import Exceptions.LibraryException;
import Model.Book;
import Model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    Librarian librarian;

    @BeforeEach
    void setUp() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void addBookToLibrary() {
        Book book1 = new Book("Biology","Emeka",3);
        Library.getAvailableBooks().put(book1.getBookTitle(), book1.getNumberOfBooks());
        librarian = new Librarian("Saha", Position.LIBRARIAN);
        final String expectedOutput = book1.getNumberOfBooks()+" new "+book1.getBookTitle()+ " added."; //Book is already in the Library";
        final String actualOutput = librarian.addBookToLibrary(book1);
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void lendBookToUserByPriority() {
        Librarian checkException = new Librarian("Me",Position.LIBRARIAN);
        Book book1 = new Book("bfj","fjfa",0);
        User user = new User("ME",Position.TEACHER);
        checkException.registerUser(user);
        Library.getAvailableBooks().put(book1.getBookTitle(),book1.getNumberOfBooks());
        Throwable exception = assertThrows(LibraryException.class, () ->{
            checkException.lendBookToUserByPriority(book1);
        });
        assertEquals("No copy of the book "+book1.getBookTitle()+" in the library",exception.getMessage());
    }

    @Test
    void lendBookToUserByFifo() {
        Librarian checkException = new Librarian("Me",Position.LIBRARIAN);
        Book book1 = new Book("bfj","fjfa",0);
        User user = new User("ME",Position.TEACHER);
        checkException.registerUser(user);
        Library.getAvailableBooks().put(book1.getBookTitle(),book1.getNumberOfBooks());
        Throwable exception = assertThrows(LibraryException.class, () ->{
            checkException.lendBookToUserByFifo(book1);
        });
        assertEquals("No copy of the book "+book1.getBookTitle()+" in the library",exception.getMessage());
    }

    @Test
    void collectBookFromUser() {
    }
}