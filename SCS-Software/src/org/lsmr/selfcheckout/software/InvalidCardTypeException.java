package org.lsmr.selfcheckout.software;

/**
 * If card not valid
 */
public class InvalidCardTypeException extends Exception {
    public InvalidCardTypeException() {
        super("InvalidCardTypeException");
    }
}
