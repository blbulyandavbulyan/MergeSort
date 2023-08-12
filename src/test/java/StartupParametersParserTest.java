import org.blbulyandavbulyan.mergesort.startupparamters.InputDataType;
import org.blbulyandavbulyan.mergesort.startupparamters.SortMode;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParametersParser;
import org.blbulyandavbulyan.mergesort.startupparamters.StartupParameters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartupParametersParserTest {
    @Test
    public void inputTypeIntegerTwoInputFiles() {
        StartupParametersParser startupParametersParser = new StartupParametersParser();
        StartupParameters startupParameters = startupParametersParser.getStartupParameters(new String[]{"-i", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.INTEGER, startupParameters.inputDataType());
        assertEquals("out.txt", startupParameters.outputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.inputFilesNames());
    }
    @Test
    public void inputTypeStringTwoInputFiles(){
        StartupParametersParser startupParametersParser = new StartupParametersParser();
        StartupParameters startupParameters = startupParametersParser.getStartupParameters(new String[]{"-s", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.inputDataType());
        assertEquals("out.txt", startupParameters.outputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.inputFilesNames());
    }
    @Test
    public void inputTypeIntegerAscendedOrder(){
        StartupParametersParser startupParametersParser = new StartupParametersParser();
        StartupParameters startupParameters = startupParametersParser.getStartupParameters(new String[]{"-s", "-a", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.inputDataType());
        assertEquals(SortMode.ASCENDED, startupParameters.sortMode());
        assertEquals("out.txt", startupParameters.outputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.inputFilesNames());
    }
    @Test
    public void inputTypeIntegerDescendedOrder(){
        StartupParametersParser startupParametersParser = new StartupParametersParser();
        StartupParameters startupParameters = startupParametersParser.getStartupParameters(new String[]{"-s", "-d", "out.txt", "in1.txt", "in2.txt"});
        assertEquals(InputDataType.STRING, startupParameters.inputDataType());
        assertEquals(SortMode.DESCENDED, startupParameters.sortMode());
        assertEquals("out.txt", startupParameters.outputFileName());
        assertEquals(List.of("in1.txt", "in2.txt"), startupParameters.inputFilesNames());
    }
}
