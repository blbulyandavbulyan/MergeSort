package org.blbulyandavbulyan.mergesort.files.iterator;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Данные итератор позволяет преобразовывать данные из оборачиваемого им итератором
 * @param <T> тип элементов в оригинальном итераторе
 * @param <R> тип элементов, возвращаемых этим итератором
 */
public class IteratorConverter<T, R> implements Iterator<R> {
    private final Iterator<T> original;
    private final Function<T, R> converter;

    /**
     * Создаёт экземпляр данного итератора
     * @param original оригинальный итератор, из которого будут браться входные данные
     * @param converter функция для преобразования данных из оригинального итератора в заданный тип
     */
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
