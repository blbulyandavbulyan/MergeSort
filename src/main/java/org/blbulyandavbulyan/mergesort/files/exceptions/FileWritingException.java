package org.blbulyandavbulyan.mergesort.files.exceptions;

/**
 * Данное исключение выбрасывается при ошибке записи в файл
 */
public class FileWritingException extends RuntimeException{
    public FileWritingException(String message, Throwable cause) {
        super(message, cause);
    }
}