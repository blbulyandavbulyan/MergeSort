package org.blbulyandavbulyan.mergesort.startupparamters;

public class StartupParameterParser {
    public StartupParameters getStartupParameters(String[] args){
        StartupParameters startupParameters = new StartupParameters();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg){
                case "-a"-> startupParameters.setSortMode(SortMode.ASCENDED);
                case "-d"-> startupParameters.setSortMode(SortMode.DESCENDED);
                case "-s"->{
                    if(startupParameters.isInputDataTypeWasSet())
                        throw new RuntimeException("Входной тип данных уже был установлен ранее!");// TODO: 07.08.2023 заменить это на конкретное исключение
                    startupParameters.setInputDataType(InputDataType.STRING);
                }
                case "-i"-> startupParameters.setInputDataType(InputDataType.INTEGER);
                default -> {
                    if(!startupParameters.hasOutputFileName()){
                        startupParameters.setOutputFileName(arg);
                    }
                    else startupParameters.addInputFileName(arg);
                }
            }
        }
        return startupParameters;
    }
}
