package exceptions;

public class NoAvailableResourcesException extends GameActionException {

    NoAvailableResourcesException() {
    }

    NoAvailableResourcesException(String s) {
        super(s);
    }

}
