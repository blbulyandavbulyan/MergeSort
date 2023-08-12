package org.blbulyandavbulyan.mergesort.files.exceptions;

import org.blbulyandavbulyan.mergesort.exceptions.ApplicationException;

/**
 * Общий класс для ошибок чтения/записи в файл
 */
public class FileOperationException extends ApplicationException {
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
