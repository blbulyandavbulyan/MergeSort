package org.blbulyandavbulyan.mergesort.exceptions;

/**
 * Данный класс предоставляет общее исключение, которое может быть выброшено в моём приложении
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
