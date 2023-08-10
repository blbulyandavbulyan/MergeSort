package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.files.FilesMerger;
import org.blbulyandavbulyan.mergesort.files.iterator.exceptions.FileWritingException;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameterParser;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        StartupParameters startupParameters = new StartupParameterParser().getStartupParameters(args);
        ConvertorResolver convertorResolver = new ConvertorResolver();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(startupParameters.outputFileName()));){
            new FilesMerger(startupParameters.inputFilesNames()).mergeFiles(convertorResolver.resolve(startupParameters.inputDataType()), (Consumer<Object>) t -> {
                try {
                    bufferedWriter.write(t.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new FileWritingException("Ошибка записи в файл " + startupParameters.outputFileName(), e);
                }
            });
        }
    }
}
