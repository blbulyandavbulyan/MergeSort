package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.processing.args.StartupParameterParser;
import org.blbulyandavbulyan.mergesort.processing.args.StartupParameters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StartupParameters startupParameters = new StartupParameterParser().getStartupParameters(args);
        ConvertorResolver convertorResolver = new ConvertorResolver();
        var result = new FilesMerger(startupParameters.getInputFilesNames()).mergeFiles(convertorResolver.resolve(startupParameters.getInputDataType()));
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(startupParameters.getOutputFileName()))){
            for(var value : result){
                bufferedWriter.write(value.toString());
                bufferedWriter.newLine();
            }
        }
    }
}
