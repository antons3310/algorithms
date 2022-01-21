package ru.ashebalkin.skypro.course2.exceptions;

public class ArrayOutOfRangeExceptions extends RuntimeException {

    public ArrayOutOfRangeExceptions() {
        super("Индекс добавляемого значение выходит за пределы фактического количества элементов или массива");
    }

    public ArrayOutOfRangeExceptions(String message) {
        super(message);
    }

    public ArrayOutOfRangeExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayOutOfRangeExceptions(Throwable cause) {
        super(cause);
    }
}
