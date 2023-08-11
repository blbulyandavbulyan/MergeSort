package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.startupparamters.SortMode;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Данный класс предоставляет метод слияния
 *
 * @author David Blbulyan
 */
public class Merging {
    /**
     * Данный метод сливает отсортированные данные с итерируемых объектов в общее место с учётом порядка сортировки
     *
     * @param iterators         итераторы, которые нужно объединить
     * @param resultAccumulator функция, которая будет аккумулировать результат
     * @param sortMode режим сортировки, он же указывает в каком порядке отсортированы входные данные в итераторах
     * @param <T>               тип сортируемого элемента, должен быть Comparable
     */
    public static <T extends Comparable<T>> void merge(Consumer<? super T> resultAccumulator, List<Iterator<T>> iterators, SortMode sortMode) {
        Iterator<T> iterator = new OverIterator<T>(iterators, sortMode);
        while (iterator.hasNext()) {
            resultAccumulator.accept(iterator.next());
        }
    }

    /**
     * Вспомогательный итератор, который берёт управление над другими итераторами, и выдаёт их в отсортированном порядке, как бы сливая их
     * Необходимо чтобы итераторы давали отсортированные данные
     * @param <T> тип значения данного итератора, он же тип всех итераторов, переданных ему в конструкторе
     */
    private static class OverIterator<T extends Comparable<T>> implements Iterator<T> {
        final List<Iterator<T>> iterators;
        private final SortMode sortMode;
        final Map<Iterator<T>, T> iteratorToItsElement;

        /**
         * Создаёт экземпляр данного итератора
         * @param iterators итераторы, которые будут использоваться для получения данных
         * @param sortMode режим сортировки
         */
        public OverIterator(List<Iterator<T>>  iterators, SortMode sortMode) {
            this.iterators = iterators.stream()
                    .filter(Iterator::hasNext)//берём только итераторы, в которых есть элементы
                    .collect(Collectors.toCollection(ArrayList::new));//делаем ArrayList т.к. мы хотим чтобы список был изменяемым
            this.sortMode = sortMode;
            iteratorToItsElement = new HashMap<>();
        }

        @Override
        public boolean hasNext() {
            //элементы могут остаться либо в map, либо в каком-то из итераторов
            return !iteratorToItsElement.isEmpty() || iterators.stream().anyMatch(Iterator::hasNext);
        }

        @Override
        public T next() {
            Iterator<Iterator<T>> iteratorIterator = iterators.iterator();
            while (iteratorIterator.hasNext()) {
                Iterator<T> tIterator = iteratorIterator.next();//берём следующий итератор
                if (!tIterator.hasNext())//если нет элементов в данном итераторе
                    iteratorIterator.remove();//то убираем его из списка
                else iteratorToItsElement.computeIfAbsent(tIterator, Iterator::next);//иначе берём следующий элемент из итератора, если мы его ещё не взяли
            }
            Stream<Map.Entry<Iterator<T>, T>> entryStream = iteratorToItsElement.entrySet().stream();
            var nextValue = (switch (sortMode){
                case ASCENDED -> entryStream.min(Map.Entry.comparingByValue());
                case DESCENDED -> entryStream.max(Map.Entry.comparingByValue());
            }).orElseThrow();//находим следующий элемент, в зависимости от порядка сортировки это может быть либо минимальный, либо максимальный
            iteratorToItsElement.remove(nextValue.getKey());//удаляем минимальный элемент из map
            return nextValue.getValue();//возвращаем его как следующий
        }
    }
}
