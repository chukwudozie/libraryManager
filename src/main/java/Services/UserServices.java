package Services;

import Exceptions.LibraryException;
import Model.Book;

public interface UserServices {

    String requestForBook(Book book) throws LibraryException;
//    public String borrowBook(Book book, Person user);


}
