package Services;

import Exceptions.LibraryException;
import Model.Book;
import Model.Library;
import Model.Person;

import java.util.function.Consumer;
import java.util.function.Function;


public interface LibraryServices  {
    void registerUser(Person person);
    String addBookToLibrary(Book book);
    String lendBookToUserByPriority(Book book) throws LibraryException;
    String lendBookToUserByFifo(Book book) throws LibraryException;
    String collectBookFromUser(Person person, String bookTitle) ;


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



}
