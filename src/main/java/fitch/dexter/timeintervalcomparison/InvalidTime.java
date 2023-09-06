package fitch.dexter.timeintervalcomparison;

public class InvalidTime extends Exception {
    private String message;

    public InvalidTime(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
