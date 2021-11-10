package ServiceImplementation;

import Enums.Position;
import Exceptions.LibraryException;
import Model.Book;
import Model.Library;
import Model.Person;
import Services.UserServices;

public class User extends Person implements UserServices {

    public User(String fName, Position position){
        super(fName,position);
    }

    @Override
    public String requestForBook(Book book){
        if(Library.getAvailableBooks().containsKey(book.getBookTitle()) && book.getNumberOfBooks() != 0){
            return this.getFirstName()+", you can borrow "+book.getBookTitle();
        } else
            return "Book not available";
    }
}
