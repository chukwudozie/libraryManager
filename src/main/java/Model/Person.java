package Model;

import Enums.Position;

import java.util.*;

public
abstract class Person {
    private String firstName;
    private String secondName;
    private Position position;
    private int priority;
    private Set<String> booksBorrowed = new HashSet<>();



    public Person(String fname, Position pos){
        firstName = fname;
        position = pos;

        switch (pos){
            case JUNIOR_STUDENT:
                this.position = pos.JUNIOR_STUDENT;
                break;
            case SENIOR_STUDENT:
                this.position= pos.SENIOR_STUDENT;
                break;
            case TEACHER:
                this.position = pos.TEACHER;
                break;
            case LIBRARIAN:
                this.position = pos.LIBRARIAN;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pos);
        }

        if(pos.equals(Position.TEACHER)){
            this.priority = 3;
        }
        else if(pos.equals(Position.SENIOR_STUDENT)){
            this.priority = 2;
        }
        else if(pos.equals(Position.JUNIOR_STUDENT)){
            this.priority= 1;
        }
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getSecondName() {return secondName;}

    public void setSecondName(String secondName) {this.secondName = secondName;}

    public Position getPosition() {return position;}

    public void setPosition(Position position) {this.position = position;}

    public int getPriority() {return priority;}

//    public void setPriority(int priority) {this.priority = priority;}

    public Set<String> getBooksBorrowed() { return booksBorrowed; }

    public void setBooksBorrowed(Set<String> booksBorrowed) { this.booksBorrowed = booksBorrowed;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return priority == person.priority && Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName) && position == person.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, position, priority);
    }

    @Override
    public String toString() {
        return  ""+firstName +  ", "+ position +
                ", booksBorrowed=" + booksBorrowed + "\n";
    }
}
