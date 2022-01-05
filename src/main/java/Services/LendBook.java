package Services;

import Model.Book;

@FunctionalInterface
public interface LendBook {

    String lendBookToUser(Book book);

    ////    The return type of ths method is the name of the functional interface
//    public LendBook lendBookPriority = (book) -> {
//        if(!priorityQueue.isEmpty() ) {
//            final Person person = priorityQueue.remove();
//            return process(person,book);
//        } else return "please register with the Library first!!!";
//    };
}
