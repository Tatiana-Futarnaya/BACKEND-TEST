package by.clevertec.model.exceptions;

public class IdProductNotFoundException extends Exception{
    private int id;

    public IdProductNotFoundException() {
    }

    public IdProductNotFoundException(String message, int id) {
        super(message);
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
