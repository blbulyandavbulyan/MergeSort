package org.blbulyandavbulyan.mergesort.startupparamters;

import java.util.Collection;

/**
 * Предоставляет параметры запуска моей программы
 * @param outputFileName имя выходного файла
 * @param inputDataType тип входных данных
 * @param inputFilesNames имена входных файлов
 * @param sortMode режим сортировки
 */
public record StartupParameters(String outputFileName, InputDataType inputDataType, Collection<String> inputFilesNames,
                                SortMode sortMode) {

}
