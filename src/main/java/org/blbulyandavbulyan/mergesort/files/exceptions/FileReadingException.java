package org.blbulyandavbulyan.mergesort.files.exceptions;

/**
 * Данное исключение выбрасывается при ошибке чтения из файла
 */
public class FileReadingException extends RuntimeException{
    public FileReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
