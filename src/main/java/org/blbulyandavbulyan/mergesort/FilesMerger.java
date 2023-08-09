package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.filelineiteration.LineIterator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class FilesMerger {
    private final Collection<String> filesNames;

    public FilesMerger(Collection<String> filesNames) {
        this.filesNames = filesNames;
    }

    public <T extends Comparable<T>> List<T> mergeFiles(Function<String, T> converter) {
        List<T> result = new ArrayList<>();
        List<T> buffer = new ArrayList<>();
        filesNames.stream().map(fileName -> {
            try {
                return new LineIterator(fileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).forEach(li -> {
            Merging.merge(() -> new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return li.hasNext();
                }

                @Override
                public T next() {
                    return converter.apply(li.next());
                }
            }, result, buffer::add);
            result.clear();
            result.addAll(buffer);
            buffer.clear();
        });
        return result;
    }
}
