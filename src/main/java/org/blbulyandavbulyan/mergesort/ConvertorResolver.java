package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.processing.args.InputDataType;

import java.util.function.Function;

public class ConvertorResolver {
    public <T extends Comparable<T>> Function<String, ? extends T> resolve(InputDataType inputDataType){
        return switch (inputDataType){
            case INTEGER -> s -> (T) Integer.valueOf(s);
            case STRING -> s -> (T) s;
        };
    }
}
