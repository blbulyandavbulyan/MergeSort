package org.blbulyandavbulyan.mergesort.files.iterator;

import java.util.Iterator;
import java.util.function.Function;

public class IteratorConverter<T, R> implements Iterator<R> {
    private final Iterator<T> original;
    private final Function<T, R> converter;

    public IteratorConverter(Iterator<T> original, Function<T, R> converter) {
        this.original = original;
        this.converter = converter;
    }

    @Override
    public boolean hasNext() {
        return original.hasNext();
    }

    @Override
    public R next() {
        return converter.apply(original.next());
    }
}
