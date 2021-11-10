package Services;

import Exceptions.LibraryException;
import Model.Book;
import Model.Person;

public interface LibraryServices  {
    void registerUser(Person person);
    String addBookToLibrary(Book book);
    String lendBookToUserByPriority(Book book) throws LibraryException;
    String lendBookToUserByFifo(Book book) throws LibraryException;
    String collectBookFromUser(Person person, String bookTitle) ;
}
