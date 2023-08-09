package org.blbulyandavbulyan.mergesort.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Класс предоставляющий построчный итератор по файлу
 */
public class LineIterator implements Iterator<String>, AutoCloseable {

    private final BufferedReader bufferedReader;
    private boolean closed = false;

    /**
     * Создаёт итератор
     * @param fileName имя файла, который будет читаться
     * @throws FileNotFoundException в случае если файл не найден
     */
    public LineIterator(String fileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
    }
    @Override
    public boolean hasNext() {
        try {
            if(!closed){
                bufferedReader.mark(1);
                if (bufferedReader.read() < 0) {
                    bufferedReader.close();
                    closed = true;
                    return false;
                };
                bufferedReader.reset();
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String next() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() throws Exception {
        if(!closed) {
            bufferedReader.close();
            closed = true;
        }
    }
}
