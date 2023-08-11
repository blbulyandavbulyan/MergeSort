package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.startupparamters.InputDataType;

import java.util.function.Function;

/**
 * Предоставляет класс для разрешения конверторов по типам входных данных
 * Конверторы используются для преобразования входных данных, если нам их необходимо сравнивать как объекты другого типа
 */
public class ConvertorResolver {
    /**
     * Метод находит требуемый конвертор по типу
     * @param inputDataType тип входных данных
     * @return функцию, преобразующую строку в заданный тип данных
     * @param <T> тип, в который будет преобразована строка
     */
    public <T extends Comparable<T>> Function<String, ? extends T> resolve(InputDataType inputDataType){
        return switch (inputDataType){
            case INTEGER -> s -> (T) Integer.valueOf(s);
            case STRING -> s -> (T) s;
        };
    }
}
