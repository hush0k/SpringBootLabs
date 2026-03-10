package kz.kbtu.tsis5v2.exceptions;

public class handleBadRequest extends RuntimeException {
    public handleBadRequest(String message) {
        super(message);
    }
}
