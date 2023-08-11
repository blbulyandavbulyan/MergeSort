package org.blbulyandavbulyan.mergesort.files;

import org.blbulyandavbulyan.mergesort.Merging;
import org.blbulyandavbulyan.mergesort.files.iterator.LineIterator;
import org.blbulyandavbulyan.mergesort.startupparamters.SortMode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Данный класс предназначен для слияния всех данных из файлов в один
 * Порядок сортировки гарантируется, если входные файлы содержали данные в отсортированном порядке
 */
public class FilesMerger{
    private final Collection<String> filesNames;

    /**
     * Создаёт экземпляр данного класса
     * @param filesNames имена файлов, содержимое которых нужно сливать
     */

    public FilesMerger(Collection<String> filesNames) {
        this.filesNames = filesNames;
    }

    /**
     * Сливает переданные в конструкторе файлы в общее место
     * @param converter функция конвертер, которая будет использоваться для трансформации строк в файле
     * @param resultAccumulator аккумулятор результата, сюда будет попадать очередной объект, полученный в ходе преобразования конвертором из строки в файле
     * @param <T> тип, который будет возвращаться конвертором и собираемый аккумулятором
     * @throws FileNotFoundException если файл не найден
     */
    public <T extends Comparable<T>> void mergeFiles(Function<String, T> converter, SortMode sortMode, Consumer<? super T> resultAccumulator) throws FileNotFoundException {
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
            Merging.merge(resultAccumulator, iterators, sortMode);
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
