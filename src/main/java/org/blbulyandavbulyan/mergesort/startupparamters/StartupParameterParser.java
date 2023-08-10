package org.blbulyandavbulyan.mergesort.startupparamters;

public class StartupParameterParser {
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
                    parametersBuilder.setInputDataType(InputDataType.STRING);
                    inputDataTypeWasSet = true;
                }
                case "-i" -> parametersBuilder.setInputDataType(InputDataType.INTEGER);
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
