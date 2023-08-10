package org.blbulyandavbulyan.mergesort.files;

import org.blbulyandavbulyan.mergesort.Merging;
import org.blbulyandavbulyan.mergesort.files.iterator.LineIterator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FilesMerger{
    private final Collection<String> filesNames;

    public FilesMerger(Collection<String> filesNames) {
        this.filesNames = filesNames;
    }

    public <T extends Comparable<T>> void mergeFiles(Function<String, T> converter, Consumer<? super T> resultAccumulator) throws FileNotFoundException {
        List<LineIterator> lineIteratorList = new ArrayList<>();
        try{
            for (String fileName : filesNames) {
                lineIteratorList.add(new LineIterator(fileName));
            }
            List<Iterator<T>> iterators = lineIteratorList.stream().map(li -> (Iterator<T>)new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return li.hasNext();
                }

                @Override
                public T next() {
                    return converter.apply(li.next());
                }
            }).toList();
            Merging.merge(resultAccumulator, iterators);
        }
        finally {
            for (LineIterator lineIterator : lineIteratorList){
                try {
                    lineIterator.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
