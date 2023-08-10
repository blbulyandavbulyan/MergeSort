package org.blbulyandavbulyan.mergesort;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
     * @param <T>               тип сортируемого элемента, должен быть Comparable
     * @param resultAccumulator функция, которая будет аккумулировать результат
     */
    public static <T extends Comparable<T>> void merge(Consumer<? super T> resultAccumulator, List<Iterator<T>> iterators) {
        Iterator<T> iterator = new OverIterator<T>(iterators);
        while (iterator.hasNext()) {
            resultAccumulator.accept(iterator.next());
        }
    }

    private static class OverIterator<T extends Comparable<T>> implements Iterator<T> {
        final List<Iterator<T>> iterators;
        final Map<Iterator<T>, T> iteratorToItsElement;

        public OverIterator(List<Iterator<T>>  iterators) {
            this.iterators = iterators.stream()
                    .filter(Iterator::hasNext)//берём только итераторы, в которых есть элементы
                    .collect(Collectors.toCollection(ArrayList::new));//делаем ArrayList т.к. мы хотим чтобы список был изменяемым
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
            var min = iteratorToItsElement.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow();//находим минимальный элемент среди элементов, взятых с каждого итератора
            iteratorToItsElement.remove(min.getKey());//удаляем минимальный элемент из map
            return min.getValue();//возвращаем его как следующий
        }
    }
}
