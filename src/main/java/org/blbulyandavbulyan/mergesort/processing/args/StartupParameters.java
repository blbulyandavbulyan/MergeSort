package org.blbulyandavbulyan.mergesort.processing.args;

import java.util.ArrayList;
import java.util.Collection;

public final class StartupParameters {
    private String outputFileName;
    private InputDataType inputDataType;
    private Collection<String> inputFilesNames = new ArrayList<>();
    private SortMode sortMode = SortMode.ASCENDED;
    boolean isInputDataTypeWasSet(){
        return inputDataType != null;
    }
    boolean hasOutputFileName(){
        return outputFileName != null;
    }
    void setOutputFileName(String outputFileName){
        this.outputFileName = outputFileName;
    }
    void setInputDataType(InputDataType inputDataType){
        this.inputDataType = inputDataType;
    }

    void setSortMode(SortMode sortMode) {
        this.sortMode = sortMode;
    }

    void addInputFileName(String inputFileName){
        this.inputFilesNames.add(inputFileName);
    }
    public String getOutputFileName() {
        return outputFileName;
    }

    public InputDataType getInputDataType() {
        return inputDataType;
    }

    public Collection<String> getInputFilesNames() {
        return inputFilesNames;
    }

    public SortMode getSortMode() {
        return sortMode;
    }

}
