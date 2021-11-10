package Services;

import Model.Book;

@FunctionalInterface
public interface LendBook {

    String lendBookToUser(Book book);
}
