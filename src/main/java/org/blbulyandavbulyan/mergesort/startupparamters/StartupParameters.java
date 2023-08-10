package org.blbulyandavbulyan.mergesort.startupparamters;

import java.util.Collection;

public record StartupParameters(String outputFileName, InputDataType inputDataType, Collection<String> inputFilesNames,
                                SortMode sortMode) {

}
