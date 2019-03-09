package core.exceptions;

public class ServiceErrorException extends Exception {
    public ServiceErrorException(String message) {
        System.out.println(message);
    }
}
