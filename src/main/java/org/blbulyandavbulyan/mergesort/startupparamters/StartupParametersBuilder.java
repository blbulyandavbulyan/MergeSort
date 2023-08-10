package org.blbulyandavbulyan.mergesort.startupparamters;

import java.util.ArrayList;
import java.util.Collection;

public class StartupParametersBuilder {
    private String outputFileName;
    private InputDataType inputDataType = InputDataType.STRING;
    private Collection<String> inputFilesNames = new ArrayList<>();
    private SortMode sortMode = SortMode.ASCENDED;

    boolean hasOutputFileName() {
        return outputFileName != null;
    }

    public StartupParametersBuilder setOutputFileName(String outputFileName) {
        if (outputFileName == null || outputFileName.isBlank())
            throw new IllegalArgumentException("outputFileName is null or blank!");
        this.outputFileName = outputFileName;
        return this;
    }

    public StartupParametersBuilder setInputDataType(InputDataType inputDataType) {
        if (inputDataType == null) throw new IllegalArgumentException("inputDataType is null!");
        this.inputDataType = inputDataType;
        return this;
    }

    public StartupParametersBuilder setSortMode(SortMode sortMode) {
        if (sortMode == null) throw new IllegalArgumentException("sortMode is null!!");
        this.sortMode = sortMode;
        return this;
    }

    public StartupParametersBuilder addInputFileName(String inputFileName) {
        if (inputFileName == null || inputFileName.isBlank())
            throw new IllegalArgumentException("inputFileName is null or blank!");
        this.inputFilesNames.add(inputFileName);
        return this;
    }

    public StartupParameters build() {
        return new StartupParameters(outputFileName, inputDataType, inputFilesNames, sortMode);
    }
}
