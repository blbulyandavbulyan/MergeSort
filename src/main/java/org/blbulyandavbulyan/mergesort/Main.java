package org.blbulyandavbulyan.mergesort;

import org.blbulyandavbulyan.mergesort.files.FilesMerger;
import org.blbulyandavbulyan.mergesort.files.exceptions.FileOperationException;
import org.blbulyandavbulyan.mergesort.files.exceptions.FileWritingException;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParametersParser;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameters;
import org.blbulyandavbulyan.mergesort.startupparamters.exceptions.StartupParametersParserException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args){
        StartupParameters startupParameters = new StartupParametersParser().getStartupParameters(args);
        ConvertorResolver convertorResolver = new ConvertorResolver();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(startupParameters.outputFileName()))){
            new FilesMerger(startupParameters.inputFilesNames()).mergeFiles(convertorResolver.resolve(startupParameters.inputDataType()), startupParameters.sortMode(), (Consumer<Object>) t -> {
                try {
                    bufferedWriter.write(t.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new FileWritingException("Ошибка записи выходной файл " + startupParameters.outputFileName(), e);
                }
            });
        }
        catch (FileNotFoundException e){
            System.err.println("Один из указанных входных файлов не найден!");
            System.err.println(e.getMessage());
        }
        catch (FileOperationException e){
            System.err.println(e.getMessage());
            System.err.println(e.getCause().getMessage());
        }
        catch (StartupParametersParserException e){
            System.err.println("Неверные аргументы командной строки");
            System.err.println(e.getMessage());
        }
        catch (IOException e){
            System.err.println("Ошибка ввода вывода");
            System.err.println(e.getMessage());
        }
    }
}
