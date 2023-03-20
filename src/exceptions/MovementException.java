package exceptions;

public class MovementException extends GameActionException {
    MovementException() {
    };

    MovementException(String s) {
        super(s);
    }
}
