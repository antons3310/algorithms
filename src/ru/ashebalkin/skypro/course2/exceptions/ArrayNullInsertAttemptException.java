package ru.ashebalkin.skypro.course2.exceptions;

public class ArrayNullInsertAttemptException extends RuntimeException{

    public ArrayNullInsertAttemptException(){
        super("Попытка добавления значения null");
    }

    public ArrayNullInsertAttemptException(String message){
        super(message);
    }

    public ArrayNullInsertAttemptException(String message, Throwable cause){
        super(message, cause);
    }

    public ArrayNullInsertAttemptException(Throwable cause){
        super(cause);
    }
}
