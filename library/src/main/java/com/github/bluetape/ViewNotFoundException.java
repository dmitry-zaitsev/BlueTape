package com.github.bluetape;

/**
 * Thrown when view cannot be found in layout.
 */
public class ViewNotFoundException extends RuntimeException {

    public ViewNotFoundException(String message) {
        super(message);
    }

}
