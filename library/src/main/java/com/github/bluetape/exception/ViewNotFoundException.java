package com.github.bluetape.exception;

/**
 * Thrown when view cannot be found in layout.
 */
public class ViewNotFoundException extends RuntimeException {

    public ViewNotFoundException(String message) {
        super(message);
    }

}
