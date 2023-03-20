package exceptions;

public class NotEnoughActionsException extends GameActionException {
    NotEnoughActionsException() {
    };

    NotEnoughActionsException(String s) {
        super(s);
    }
}
