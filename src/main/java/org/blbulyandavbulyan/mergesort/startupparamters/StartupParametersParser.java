package org.blbulyandavbulyan.mergesort.startupparamters;

import org.blbulyandavbulyan.mergesort.startupparamters.exceptions.DuplicateTypeArgumentException;

/**
 * Данный класс предназначен для обработки аргументов командной строки и формирования объекта с параметрами
 */
public class StartupParametersParser {
    /**
     * Обрабатывает аргументы командной строки
     *
     * @param args аргументы командной строки
     * @return объект, содержащий параметры в удобном для программы представлении
     * @throws DuplicateTypeArgumentException если в аргументах встречается указания нескольких типов входных данных или дублирующиеся указания
     */
    public StartupParameters getStartupParameters(String[] args) {
        StartupParametersBuilder parametersBuilder = new StartupParametersBuilder();
        boolean inputDataTypeWasSet = false;
        for (String arg : args) {
            switch (arg) {
                case "-a" -> parametersBuilder.setSortMode(SortMode.ASCENDED);
                case "-d" -> parametersBuilder.setSortMode(SortMode.DESCENDED);
                case "-s" -> {
                    if (inputDataTypeWasSet)
                        throw new DuplicateTypeArgumentException("Входной тип данных уже был установлен ранее!");
                    inputDataTypeWasSet = true;
                    parametersBuilder.setInputDataType(InputDataType.STRING);
                }
                case "-i" -> {
                    if (inputDataTypeWasSet)
                        throw new DuplicateTypeArgumentException("Входной тип данных уже был установлен ранее!");
                    inputDataTypeWasSet = true;
                    parametersBuilder.setInputDataType(InputDataType.INTEGER);
                }
                default -> {
                    if (!parametersBuilder.hasOutputFileName()) {
                        parametersBuilder.setOutputFileName(arg);
                    } else parametersBuilder.addInputFileName(arg);
                }
            }
        }
        return parametersBuilder.build();
    }
}
