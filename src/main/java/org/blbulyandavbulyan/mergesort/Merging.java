package org.blbulyandavbulyan.mergesort;

import java.util.function.Consumer;

/**
 * Данный класс предоставляет метод слияния
 * @author David Blbulyan
 */
public class Merging {
    //данный код взят из другого моего проекта, дабы не писать его заново
    /**
     * Данный метод предоставляет возможность слить два отсортированных итериреумых объектов
     * @param iterable1  первое итерируемое
     * @param iterable2  второе итерируемое
     * @param <T> тип сортируемого элемента, должен быть Comparable
     * @param resultAccumulator функция, которая будет аккумулировать результат
     */
    public static <T extends Comparable<T>> void merge(Iterable<T> iterable1, Iterable<T> iterable2, Consumer<? super T> resultAccumulator) {
        //на самом деле, слияние двух списков оказалось не такой уж тривиальной задачей, особенно написание комментариев
//        List<T> result = new ArrayList<>(iterable1.size() + iterable2.size());//собственно результат, в который будут сливаться элементы
        var firstIter = iterable1.iterator();
        var secondIter = iterable2.iterator();
        //в этой переменной если null, то значит последнего не добавленного значения нет
        T lastNotAddedValue = null;//последнее не добавленное значение, тут большее всегда, поскольку мы добавляем меньшие
        {
            boolean needNextFirst = true;// нужно ли вызывать next у первого итератора
            boolean needNextSecond = true;//нужно ли вызывать next у второго итератора
            T first = null;//элемент из первого списка
            T second = null;//элемент из второго списка
            while (firstIter.hasNext() && secondIter.hasNext()) {
                //двигаем итератор по первому и по второму списку, если нужно
                if (needNextFirst) first = firstIter.next();
                if (needNextSecond) second = secondIter.next();
                int comparingResult = first.compareTo(second);//мы сравниваем два элемента
                //далее, весь смысл в том, что мы должны записать в результат меньший элемент или first или second
                //или записать оба, если они равны
                if (comparingResult < 0) {//first < second
                    resultAccumulator.accept(first);//поскольку первый меньший, записываем его
                    //теперь, вдруг у нас в первом списке есть ещё элементы, которые меньше second
                    //мы должны на данном этапе получать только их
                    needNextFirst = true;
                    needNextSecond = false;
                } else if (comparingResult > 0) {//first > second
                    resultAccumulator.accept(second);//записываем элемент из второго списка, поскольку он меньше
                    needNextFirst = false;
                    //далее поскольку мы записали элемент из второго списка, значит далее мы будем брать только из него
                    needNextSecond = true;
                } else {//first == second
                    //элементы из первого и второго списка равны, записываем оба
                    resultAccumulator.accept(first);
                    resultAccumulator.accept(second);
                    //здесь, поскольку мы оба записали, то нам нужно взять следующие элементы из двух списков сразу
                    needNextFirst = needNextSecond = true;
                }
                //это условие нужно, если у нас внезапно закончились элементы из списка, то нам нужно добавить максимальный из двух first или second, поскольку мы добавляем всегда минимальный из first и second
                //на предыдущих шагах
                //если у нас закончился один из списков, и результат сравнения first и second дал нам что они не равны, то
                if ((!firstIter.hasNext() || !secondIter.hasNext()) && comparingResult != 0) {
                    lastNotAddedValue = comparingResult > 0 ? first : second;//если comparingResult > 0 -> first > second и нам нужен first, иначе нам нужен second
                    //если закончились оба списка, то добавляем сразу
                    //это нужно для того, чтобы добавить его, т.к. наш следующий while(вставляющий оставшиеся элементы в одном из списков), который должен был его вставить в правильном месте не выполнится
                    if (!firstIter.hasNext() && !secondIter.hasNext())
                        resultAccumulator.accept(lastNotAddedValue);
                }
            }
            //здесь мы выбираем итератор того списка, который мы обошли не до конца в результате первого цикла
            var iter = firstIter.hasNext() ? firstIter : secondIter;
            //собственно если в нём остались элементы, обходим и добавляем
            while (iter.hasNext()) {
                T current = iter.next();
                //поскольку в lastNotAddedValue может лежать последнее недобавленное значение, которое осталось в результате работы первого цикла,
                //то нам нужно добавить его здесь
                //мы считаем что у нас оно есть, если != null
                int comparing = lastNotAddedValue != null ? current.compareTo(lastNotAddedValue) : -1;
                if (comparing < 0) resultAccumulator.accept(current);//добавляем все значения, меньшие нашего lastNotAddedValue ну или если его нет
                else {//нашли значение которое больше lastNotAddedValue
                    //добавляем сначала меньше, потом большее
                    resultAccumulator.accept(lastNotAddedValue);
                    resultAccumulator.accept(current);
                    lastNotAddedValue = null;//теперь у нас больше нет последнего не добавленного значения, мы его добавили
                }
                //на случай если у нас оно оказалось самым большим, то нам нужно добавить его после всех
                if (!iter.hasNext() && lastNotAddedValue != null) resultAccumulator.accept(lastNotAddedValue);
            }

        }
    }
}
