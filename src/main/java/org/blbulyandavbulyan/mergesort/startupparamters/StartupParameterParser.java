package org.blbulyandavbulyan.mergesort.startupparamters;

/**
 * Данный класс предназначен для обработки аргументов командной строки и формирования объекта с параметрами
 */
public class StartupParameterParser {
    /**
     * Обрабатывает аргументы командной строки
     * @param args аргументы командной строки
     * @return объект, содержащий параметры в удобном для программы представлении
     */
    public StartupParameters getStartupParameters(String[] args){
        StartupParametersBuilder parametersBuilder = new StartupParametersBuilder();
        boolean inputDataTypeWasSet = false;
        for (String arg : args) {
            switch (arg) {
                case "-a" -> parametersBuilder.setSortMode(SortMode.ASCENDED);
                case "-d" -> parametersBuilder.setSortMode(SortMode.DESCENDED);
                case "-s" -> {
                    if (inputDataTypeWasSet)
                        throw new RuntimeException("Входной тип данных уже был установлен ранее!");// TODO: 07.08.2023 заменить это на конкретное исключение
                    inputDataTypeWasSet = true;
                    parametersBuilder.setInputDataType(InputDataType.STRING);
                }
                case "-i" -> {
                    if (inputDataTypeWasSet)
                        throw new RuntimeException("Входной тип данных уже был установлен ранее!");// TODO: 07.08.2023 заменить это на конкретное исключение
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
